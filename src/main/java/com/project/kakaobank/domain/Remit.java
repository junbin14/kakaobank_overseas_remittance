package com.project.kakaobank.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Remit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String targetCountry; //US
    @Column(nullable = false)
    private String targetCurrency; //USD
    @Column(nullable = false)
    private BigDecimal foreignAmount;
    @Column(nullable = false)
    private int exchangeRate;
    @Column(nullable = false)
    private BigDecimal totalAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "senderId", referencedColumnName = "id")
    private User user;
    @Column(nullable = false)
    private Long senderAccountNumber;
    @Column(nullable = false)
    private String receiverName;
    @Column(nullable = false)
    private Long receiverAccountNumber;
    @Column(nullable = false)
    private String receiverEmail;
    @Column(nullable = false)
    private String receiverAddress;
    @CreatedDate
    private LocalDateTime createDateTime;


    @Builder Remit(User user,
                   String targetCountry,
                   String targetCurrency,
                   BigDecimal foreignAmount,
                   int exchangeRate,
                   BigDecimal totalAmount,
                   Long senderAccountNumber,
                   String receiverName,
                   Long receiverAccountNumber,
                   String receiverEmail,
                   String receiverAddress){

        this.user = user;
        this.targetCountry = targetCountry;
        this.targetCurrency = targetCurrency;
        this.foreignAmount = foreignAmount;
        this.exchangeRate = exchangeRate;
        this.totalAmount = totalAmount;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverName = receiverName;
        this.receiverAccountNumber = receiverAccountNumber;
        this.receiverEmail = receiverEmail;
        this.receiverAddress = receiverAddress;

    }


}