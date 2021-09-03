package com.eklanfar.library.controller;

import com.eklanfar.library.model.request.IdentityScanRequest;
import com.eklanfar.library.service.IdentityScanService;
import com.eklanfar.library.util.Validator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("identityScans")
public class IdentityScanController {

    private final IdentityScanService identityScanService;

    IdentityScanController(IdentityScanService identityScanService) {
        this.identityScanService = identityScanService;
    }

    @PostMapping()
    public void update(@Valid @RequestBody final IdentityScanRequest request, BindingResult result) {
        Validator.validateRequest(result);
        identityScanService.identityScan(request);
    }
}
