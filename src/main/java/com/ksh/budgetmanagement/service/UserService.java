package com.ksh.budgetmanagement.service;

import com.ksh.budgetmanagement.controller.request.SignupRequest;
import com.ksh.budgetmanagement.exception.CustomException;
import com.ksh.budgetmanagement.exception.ErrorCode;
import com.ksh.budgetmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequest request){
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_USED_EMAIL);
        }
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new CustomException(ErrorCode.ALREADY_USED_NICKNAME);
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request.toEntity());
    }
}
