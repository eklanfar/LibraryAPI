package com.eklanfar.library.retrofit.microblink.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class MRTDResponse {

    private String executionId;
    private Date finishTime;
    private Date startTime;
    private Result result;
}
