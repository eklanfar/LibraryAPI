package com.eklanfar.library.service;

import com.eklanfar.library.exception.ExceptionHandler;
import com.eklanfar.library.model.request.UserCreateRequest;
import com.eklanfar.library.model.request.UserUpdateRequest;
import com.eklanfar.library.model.response.UserCreateResponse;
import com.eklanfar.library.model.response.UserListResponse;
import com.eklanfar.library.model.response.UserMostLateResponse;
import com.eklanfar.library.repository.UserRepository;
import com.eklanfar.library.util.Helper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserListResponse> getUserList() {
        List<UserListResponse> response = new ArrayList<>();

        try {
            response = userRepository.getList();
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return response;
    }

    public UserCreateResponse createUser(final UserCreateRequest request) {
        UserCreateResponse response = null;

        try {
            Map<String, Object> inParam = Helper.fromObjectToMap(request);

            response = userRepository.create(inParam);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return response;
    }

    public void updateUser(final Integer userId, final UserUpdateRequest request) {
        try {
            Map<String, Object> inParam = Helper.fromObjectToMap(request);
            inParam.put("id", userId);

            userRepository.update(inParam);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    public UserMostLateResponse getMostLate() {
        UserMostLateResponse response = null;

        try {
            response = userRepository.getMostLate();
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return response;
    }
}
