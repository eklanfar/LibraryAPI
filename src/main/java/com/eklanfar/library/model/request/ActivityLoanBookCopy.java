package com.eklanfar.library.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ActivityLoanBookCopy {

    @NotNull
    private Long bookCopyId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
}
