package com.ksh.budgetmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;

    @Column(columnDefinition = "VARCHAR(30)" , nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(100)" , nullable = false, unique = true, updatable = false)
    private String email;

    @Column(columnDefinition = "VARCHAR(100)" , nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(30)" , nullable = false, unique = true)
    private String nickname;

    @Column(name = "is_alarm_received", columnDefinition = "BOOLEAN", nullable = false)
    private boolean isAlarmReceived;

    @Column(name = "is_withdrawal", columnDefinition = "BOOLEAN", nullable = false)
    private boolean isWithdrawal;

}
