package com.eklanfar.library.model.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ActivityLoanBookRequest {

    @NotNull
    private Integer userId;
    @Valid
    @NotEmpty
    private List<ActivityLoanBookCopy> bookCopies;
}
