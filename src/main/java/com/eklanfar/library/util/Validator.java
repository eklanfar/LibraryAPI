package com.eklanfar.library.util;

import com.eklanfar.library.exception.BadRequestException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class Validator {

    /**
     * Check if binding result has any errors and throw exception if it is a case
     *
     * @param result result to be validated
     */
    public static void validateRequest(final BindingResult result) {
        if (result.hasFieldErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            throw new BadRequestException(Constants.ERROR_MISSING_REQUIRED_PARAMETER + errorsToString(errors));
        }
    }

    /**
     * Convert list of errors to string
     *
     * @param errors list of errors
     * @return list of errors in string format
     */
    public static String errorsToString(final List<FieldError> errors) {
        StringBuilder sb = new StringBuilder();
        String delim = "";
        for (FieldError error : errors) {
            sb.append(delim).append(error.getField()).append(" ").append(error.getDefaultMessage());
            delim = ", ";
        }
        return " (" + sb + ")";
    }

    private Validator() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
