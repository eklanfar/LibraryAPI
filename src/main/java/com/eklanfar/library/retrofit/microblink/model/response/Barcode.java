package com.eklanfar.library.retrofit.microblink.model.response;

import lombok.Data;

import java.util.List;

@Data
public class Barcode {

    private String rawDataBase64;
    private String stringData;
    private String firstName;
    private String lastName;
    private String middleName;
    private String fullName;
    private String additionalNameInformation;
    private String address;
    private String placeOfBirth;
    private String nationality;
    private String race;
    private String religion;
    private String profession;
    private String maritalStatus;
    private String residentialStatus;
    private String employer;
    private String sex;
    private DateOfBirth dateOfBirth;
    private DateOfIssue dateOfIssue;
    private DateOfExpiry dateOfExpiry;
    private String documentNumber;
    private String personalIdNumber;
    private String documentAdditionalNumber;
    private String issuingAuthority;
    private AddressDetailedInfo addressDetailedInfo;
    private DriverLicenseDetailedInfo driverLicenseDetailedInfo;
    private List<Object> extendedElements;
}
