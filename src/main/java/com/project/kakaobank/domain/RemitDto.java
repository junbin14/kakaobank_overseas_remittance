package com.project.kakaobank.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class RemitDto {

    private BigDecimal foreignAmount;
    private String targetCountry;
    private String targetCurrency;
    private String userEmail;
    private Long senderAccountNumber;
    private String receiverName;
    private Long receiverAccountNumber;
    private String receiverEmail;
    private String receiverAddress;
}
