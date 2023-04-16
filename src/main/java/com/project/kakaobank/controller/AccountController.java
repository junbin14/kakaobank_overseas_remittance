package com.project.kakaobank.controller;

import com.project.kakaobank.domain.Account;
import com.project.kakaobank.domain.AccountDto;
import com.project.kakaobank.service.AccountService;
import com.project.kakaobank.domain.User;
import com.project.kakaobank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;
    @Autowired
    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/account/create")
    @ResponseBody
    public void create(@RequestBody AccountDto accountDto){
        User user = userService.findByEmail(accountDto.getUserEmail());
        Account account = Account.builder()
                .user(user)
                .balance(accountDto.getBalance())
                .accountNumber(accountDto.getAccountNumber())
                .build();
        accountService.save(account);
    }
}
