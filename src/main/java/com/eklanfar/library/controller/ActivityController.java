package com.eklanfar.library.controller;

import com.eklanfar.library.model.request.ActivityLoanBookRequest;
import com.eklanfar.library.model.response.ActivityBookLoanHistoryResponse;
import com.eklanfar.library.service.ActivityService;
import com.eklanfar.library.util.Validator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("activities")
public class ActivityController {

    private final ActivityService activityService;

    ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/loan")
    public void loan(@Valid @RequestBody final ActivityLoanBookRequest request, BindingResult result) {
        Validator.validateRequest(result);
        activityService.loanBookCopy(request);
    }

    @GetMapping("/book/{bookId}/loanHistory")
    public List<ActivityBookLoanHistoryResponse> getLoanHistory(@PathVariable final Long bookId) {
        return activityService.getLoanHistory(bookId);
    }
}
