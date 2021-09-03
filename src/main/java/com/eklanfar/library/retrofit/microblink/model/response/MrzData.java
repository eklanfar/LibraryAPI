package com.eklanfar.library.retrofit.microblink.model.response;

import lombok.Data;

@Data
public class MrzData {

    private String rawMrzString;
    private String documentCode;
    private String issuer;
    private String documentNumber;
    private String opt1;
    private String opt2;
    private String gender;
    private String nationality;
    private String primaryId;
    private String secondaryId;
    private String alienNumber;
    private String applicationReceiptNumber;
    private String immigrantCaseNumber;
    private boolean mrzVerified;
    private boolean mrzParsed;
    private DateOfBirth dateOfBirth;
    private DateOfExpiry dateOfExpiry;
    private String documentType;
    private String issuerName;
    private String nationalityName;
}
