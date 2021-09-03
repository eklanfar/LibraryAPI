package com.eklanfar.library.retrofit.microblink.model.response;

import lombok.Data;

@Data
public class Result {

    private DateOfBirth dateOfBirth;
    private ClassInfo classInfo;
    private String type;
    private boolean isBelowAgeLimit;
    private int age;
    private String recognitionStatus;
    private String firstName;
    private String lastName;
    private String fullName;
    private String address;
    private DateOfIssue dateOfIssue;
    private DateOfExpiry dateOfExpiry;
    private String documentNumber;
    private String sex;
    private DriverLicenseDetailedInfo driverLicenseDetailedInfo;
    private String fullDocumentImageBase64;
    private String faceImageBase64;
    private String additionalNameInformation;
    private String additionalAddressInformation;
    private String placeOfBirth;
    private String nationality;
    private String race;
    private String religion;
    private String profession;
    private String maritalStatus;
    private String residentialStatus;
    private String employer;
    private String personalIdNumber;
    private String documentAdditionalNumber;
    private String documentOptionalAdditionalNumber;
    private String issuingAuthority;
    private MrzData mrzData;
    private String conditions;
    private String localizedName;
    private boolean dateOfExpiryPermanent;
    private String additionalPersonalIdNumber;
    private Viz viz;
    private Barcode barcode;
    private ImageAnalysisResult imageAnalysisResult;
    private String processingStatus;
    private String recognitionMode;
    private String signatureImageBase64;
}
