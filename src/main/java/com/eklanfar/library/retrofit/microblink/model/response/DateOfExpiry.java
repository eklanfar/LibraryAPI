package com.eklanfar.library.retrofit.microblink.model.response;

import lombok.Data;

@Data
public class DateOfExpiry {

    private int day;
    private int month;
    private int year;
    private boolean successfullyParsed;
    private String originalString;
}