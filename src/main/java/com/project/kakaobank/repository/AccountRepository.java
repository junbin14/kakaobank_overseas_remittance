package com.project.kakaobank.repository;

import com.project.kakaobank.domain.Account;
import com.project.kakaobank.domain.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(Long accountNumber);
}
