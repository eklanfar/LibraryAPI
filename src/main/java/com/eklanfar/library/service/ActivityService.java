package com.eklanfar.library.service;

import com.eklanfar.library.exception.ExceptionHandler;
import com.eklanfar.library.model.request.ActivityLoanBookRequest;
import com.eklanfar.library.model.response.ActivityBookLoanHistoryResponse;
import com.eklanfar.library.repository.ActivityRepository;
import com.eklanfar.library.util.Helper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void loanBookCopy(final ActivityLoanBookRequest request) {
        try {
            Map<String, Object> inParam = new HashMap<>();
            inParam.put("userId", request.getUserId());
            inParam.put("bookCopiesJson", Helper.asJson(request.getBookCopies()));
            activityRepository.loan(inParam);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    public List<ActivityBookLoanHistoryResponse> getLoanHistory(final Long bookId) {
        List<ActivityBookLoanHistoryResponse> response = null;
        try {
            Map<String, Object> inParam = new HashMap<>();
            inParam.put("bookId", bookId);
            response = activityRepository.getLoanHistory(inParam);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }

        return response;
    }
}
