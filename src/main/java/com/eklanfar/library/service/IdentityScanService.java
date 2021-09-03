package com.eklanfar.library.service;

import com.eklanfar.library.exception.ExceptionHandler;
import com.eklanfar.library.exception.UnknownException;
import com.eklanfar.library.exception.microblink.MRTDValidationException;
import com.eklanfar.library.model.request.IdentityScanRequest;
import com.eklanfar.library.repository.IdentityScanRepository;
import com.eklanfar.library.retrofit.RetrofitHelper;
import com.eklanfar.library.retrofit.microblink.MicroblinkAPI;
import com.eklanfar.library.retrofit.microblink.model.request.MRTDRequest;
import com.eklanfar.library.retrofit.microblink.model.response.MRTDResponse;
import com.eklanfar.library.util.Constants;
import com.eklanfar.library.util.Enums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class IdentityScanService {

    private final IdentityScanRepository identityScanRepository;

    @Value("${api.microblink.baseUrl}")
    private String microblinkBaseUrl;
    @Value("${api.microblink.timeout}")
    private Integer microblinkTimeout;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    IdentityScanService(IdentityScanRepository identityScanRepository) {
        this.identityScanRepository = identityScanRepository;
    }

    public void identityScan(final IdentityScanRequest request) {
        try {
            Response<MRTDResponse> response;

            final MRTDRequest apiRequest = new MRTDRequest(request.getImagePath());
            final MicroblinkAPI microblinkAPI = RetrofitHelper.getMicroblinkAPI(microblinkBaseUrl, microblinkTimeout);
            // bearer token hardcoded for testing purposes
            final Call<MRTDResponse> call = microblinkAPI.mrtd("Bearer OWRlYjAxY2YwODg0NDcwZWJiNDIwY2Y5NTNiMjA5OTg6NDM0ZDIwMmUtOTE2YS00ZmZlLTg3NWQtNjQ3Mjk2MTVkMDJj", apiRequest);

            response = call.execute();

            // validate response
            if (response.isSuccessful() && response.body() != null) {
                MRTDResponse responseBody = response.body();
                final boolean mrzValid = parseAndValidateMRZData(responseBody.getResult().getMrzData().getRawMrzString().replace("\n", ""));

                Map<String, Object> inParam = new HashMap<>();
                inParam.put("valid", mrzValid);
                inParam.put("firstName", responseBody.getResult().getFirstName());
                inParam.put("lastName", responseBody.getResult().getLastName());
                inParam.put("dateOfBirth", responseBody.getResult().getDateOfBirth().getOriginalString());

                identityScanRepository.create(inParam);
            } else if (!response.isSuccessful() && response.errorBody() != null) {
                log.info("FAILED - identityScan");
                log.error("FAILED - identityScan; {}", RetrofitHelper.parseRetrofitErrorBody(response));
            } else {
                throw new UnknownException("Identity scan was not successful");
            }

        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * Validation and calculation are done by assuming that we're using only TD1
     *
     * @param rawData raw MRZ data received from Microblink API.
     *                Example: IOHRV123456789701234567890<<<<7701018M2006017HRV<<<<<<<<<<<7UZORAK<<SPECIMEN<<<<<<<<<<<<<<
     */
    private boolean parseAndValidateMRZData(final String rawData) {
        log.info("ENTERED - parseAndValidateMRZData; rawData = {}", rawData);
        try {
            final String line1 = rawData.substring(0, 30);
            final String line2 = rawData.substring(30, 60);
            final String overallCheckDigit = rawData.substring(59, 60);
            String overallLine = "";

            // validate line1
            overallLine += validateLine(line1);
            // validate line2
            overallLine += validateLine(line2);
            // validate overall checkDigit
            validateCheckDigit(overallLine, overallCheckDigit);
            log.info("PROCESSED - parseAndValidateMRZData");
        } catch (Exception e) {
            log.info("FAILED - parseAndValidateMRZData; {}", e.getMessage());
            log.error("EXCEPTION - parseAndValidateMRZData", e);
            return false;
        }
        return true;
    }

    /**
     * Validate MRZ line.
     *
     * @param line to be validated
     * @return data needed for overall check
     */
    private String validateLine(final String line) {
        log.info("ENTERED - validateLine; line = {}", line);
        String response;

        final Pattern p1 = Pattern.compile(Constants.TD1_LINE1);
        final Matcher m1 = p1.matcher(line);

        final Pattern p2 = Pattern.compile(Constants.TD1_LINE2);
        final Matcher m2 = p2.matcher(line);

        if (m1.matches()) {
            // line1 detected
            final String docNum = m1.group(3);
            final String docNumCheck = m1.group(4);
            final String optional = m1.group(5);

            validateCheckDigit(docNum, docNumCheck);
            response = docNum + docNumCheck + optional;
        } else if (m2.matches()) {
            // line2 detected
            final String dob = m2.group(1);
            final String dobCheck = m2.group(2);
            final String expireDate = m2.group(4);
            final String expireDateCheck = m2.group(5);
            final String optional = m2.group(7);

            validateCheckDigit(expireDate, expireDateCheck);
            response = dob + dobCheck + expireDate + expireDateCheck + optional;
        } else {
            throw new MRTDValidationException("No MRZ line pattern was matched");
        }

        log.info("PROCESSED - validateLine; response = {}", response);
        return response;
    }

    /**
     * Validate check digit for sent data
     *
     * @param data       data from which check digit will be calculated
     * @param checkDigit check digit received from Microblink API
     */
    private void validateCheckDigit(String data, String checkDigit) {
        log.info("ENTERED - validateCheckDigit; data = {}, checkDigit = {}", data, checkDigit);

        // order of digit weights is repeatedly 7,3,1,7,3,1,7...
        final List<Integer> digitWeightings = Arrays.asList(7, 3, 1);
        final String[] digits = data.split("");

        int digitValue;
        int sumOfMultiplications = 0;

        for (int i = 0; i < digits.length; i++) {
            digitValue = Enums.DigitValue.getValueByDigit(digits[i]);
            sumOfMultiplications += digitValue * digitWeightings.get(i % 3);
        }

        String calculatedCheckDigit = String.valueOf(sumOfMultiplications % 10);
        if (!checkDigit.equals(calculatedCheckDigit)) {
            throw new MRTDValidationException("Invalid checkDigit validation, calculated checkDigit = " + calculatedCheckDigit + ", received checkDigit = " + checkDigit);
        }

        log.info("PROCESSED - validateCheckDigit");
    }
}
