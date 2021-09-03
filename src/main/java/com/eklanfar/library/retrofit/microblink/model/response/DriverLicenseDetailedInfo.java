package com.eklanfar.library.retrofit.microblink.model.response;

import lombok.Data;

@Data
public class DriverLicenseDetailedInfo {

    private String restrictions;
    private String endorsements;
    private String vehicleClass;
    private String conditions;
}
