package com.project.kakaobank.service;

import com.project.kakaobank.domain.Account;
import com.project.kakaobank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void save(Account account){
        accountRepository.save(account);
    }
    public Account findByAccountNumber(Long accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
