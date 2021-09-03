package com.eklanfar.library.retrofit.microblink.model.response;

import lombok.Data;

@Data
public class Viz {

    private String firstName;
    private String lastName;
    private String fullName;
    private String additionalNameInformation;
    private String localizedName;
    private String address;
    private String additionalAddressInformation;
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
    private boolean dateOfExpiryPermanent;
    private String documentNumber;
    private String personalIdNumber;
    private String documentAdditionalNumber;
    private String additionalPersonalIdNumber;
    private String documentOptionalAdditionalNumber;
    private String issuingAuthority;
    private DriverLicenseDetailedInfo driverLicenseDetailedInfo;
    private String conditions;
}
