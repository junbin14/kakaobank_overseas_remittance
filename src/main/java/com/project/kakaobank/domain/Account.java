package com.project.kakaobank.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userId", referencedColumnName = "id")
    private User user;
    @Setter
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    private Long accountNumber;
    @CreatedDate
    private LocalDateTime createdDate;


    @Builder
    public Account(User user, BigDecimal balance, Long accountNumber){
        this.user = user;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

}
