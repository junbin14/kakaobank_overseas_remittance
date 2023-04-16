package com.project.kakaobank.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class AccountDto {
    private String userEmail;
    private BigDecimal balance;
    private Long accountNumber;
}
