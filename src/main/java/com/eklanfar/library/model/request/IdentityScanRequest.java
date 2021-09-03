package com.eklanfar.library.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class IdentityScanRequest {

    @NotBlank
    private String imagePath;
}
