package com.project.kakaobank.controller;

import com.project.kakaobank.domain.Account;
import com.project.kakaobank.domain.AccountDto;
import com.project.kakaobank.service.AccountService;
import com.project.kakaobank.domain.User;
import com.project.kakaobank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    @GetMapping("/account/balance/check")
    @ResponseBody
    public BigDecimal balanceCheck(@RequestParam(value = "userId")Long userId, @RequestParam(value = "accountNumber")Long accountNumber) throws Exception{
        Account account = accountService.findByAccountNumber(accountNumber);
        System.out.println(account.getUser().getId());
        System.out.println(userId);

        if (account.getUser().getId().equals(userId)){
            return account.getBalance();

        } else {
            throw new Exception("accont.getUser().getId() is different from userId");
        }
    }
}
