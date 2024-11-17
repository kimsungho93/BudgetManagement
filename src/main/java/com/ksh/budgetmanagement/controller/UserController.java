package com.ksh.budgetmanagement.controller;

import com.ksh.budgetmanagement.controller.request.SignupRequest;
import com.ksh.budgetmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public void signUp(@RequestBody SignupRequest request) {
        userService.signup(request);
    }
}
