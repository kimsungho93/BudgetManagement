package com.ksh.budgetmanagement.service;

import com.ksh.budgetmanagement.controller.request.SignupRequest;
import com.ksh.budgetmanagement.exception.CustomException;
import com.ksh.budgetmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignupSuccess() {
        SignupRequest request = new SignupRequest();
        request.setEmail("test@example.com");
        request.setNickname("testuser");
        request.setPassword("password");


        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepository.existsByNickname(request.getNickname())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        userService.signup(request);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testSignupEmailAlreadyUsed() {
        SignupRequest request = new SignupRequest();
        request.setEmail("test@example.com");
        request.setNickname("testuser");
        request.setPassword("password");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        CustomException exception = assertThrows(CustomException.class, () -> userService.signup(request));
        assertEquals("이미 사용중인 이메일입니다.", exception.getMessage());
    }

    @Test
    void testSignupNicknameAlreadyUsed() {
        SignupRequest request = new SignupRequest();
        request.setEmail("test@example.com");
        request.setNickname("testuser");
        request.setPassword("password");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepository.existsByNickname(request.getNickname())).thenReturn(true);

        CustomException exception = assertThrows(CustomException.class, () -> userService.signup(request));
        assertEquals("이미 사용중인 닉네임입니다.", exception.getMessage());
    }
}