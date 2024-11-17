package com.ksh.budgetmanagement.controller.request;

import com.ksh.budgetmanagement.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {
    private String name;
    private String nickname;
    private String email;
    @Setter
    private String password;
    private boolean isAgree;

    public User toEntity() {
        return User.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .isAlarmReceived(isAgree)
                .isWithdrawal(false)
                .build();
    }
}
