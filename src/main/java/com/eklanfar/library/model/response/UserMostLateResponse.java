package com.eklanfar.library.model.response;

import lombok.Data;

@Data
public class UserMostLateResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer daysLate;
}
