package com.project.kakaobank.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class RecentReceiverInfoDto {
    private String receiverName;
    private Long receiverAccountNumber;
    private String receiverEmail;
    private String receiverAddress;
    private LocalDateTime createdDate;
}
