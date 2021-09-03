package com.eklanfar.library.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActivityBookLoanHistoryResponse {

    private String firstname;
    private String lastName;
    private Long bookCopyId;
    private LocalDate dateFrom;
    private LocalDate returnDate;
    private LocalDate expirationDate;
}
