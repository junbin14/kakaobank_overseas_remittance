package com.project.kakaobank.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int percentage;
    @Setter
    private BigDecimal totalRemittanceAmount;
    @Setter
    private String fileUploadYN;
    @CreatedDate
    private LocalDateTime createdDate;


    @Builder User(String name, String email, String password, String address, int percentage, String fileUploadYN){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.percentage = percentage;
        this.totalRemittanceAmount = BigDecimal.ZERO;
        this.fileUploadYN = fileUploadYN;
    }

}
