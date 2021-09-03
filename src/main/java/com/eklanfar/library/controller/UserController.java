package com.eklanfar.library.controller;

import com.eklanfar.library.model.request.UserCreateRequest;
import com.eklanfar.library.model.request.UserUpdateRequest;
import com.eklanfar.library.model.response.UserCreateResponse;
import com.eklanfar.library.model.response.UserListResponse;
import com.eklanfar.library.model.response.UserMostLateResponse;
import com.eklanfar.library.service.UserService;
import com.eklanfar.library.util.Validator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserListResponse> getList() {
        return userService.getUserList();
    }

    @PostMapping()
    public UserCreateResponse create(@Valid @RequestBody final UserCreateRequest request, BindingResult result) {
        Validator.validateRequest(result);
        return userService.createUser(request);
    }

    @PutMapping("/{userId}")
    public void update(@PathVariable Integer userId, @Valid @RequestBody final UserUpdateRequest request, BindingResult result) {
        Validator.validateRequest(result);
        userService.updateUser(userId, request);
    }

    @GetMapping("/mostLate")
    public UserMostLateResponse getMostLate() {
        return userService.getMostLate();
    }
}
