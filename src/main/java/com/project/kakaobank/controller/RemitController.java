package com.project.kakaobank.controller;

import com.project.kakaobank.domain.Account;
import com.project.kakaobank.domain.Remit;
import com.project.kakaobank.service.AccountService;
import com.project.kakaobank.domain.RemitDto;
import com.project.kakaobank.service.RemitService;
import com.project.kakaobank.domain.User;
import com.project.kakaobank.service.UserService;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class RemitController {
    private final RemitService remitService;
    private final UserService userService;
    private final AccountService accountService;

    public RemitController(RemitService remitService, UserService userService, AccountService accountService) {
        this.remitService = remitService;
        this.userService = userService;
        this.accountService = accountService;
    }

    @PostMapping("/remit")
    @ResponseBody
    public void remit(@RequestBody RemitDto remitDto) throws Exception {
        User user = userService.findByEmail(remitDto.getUserEmail());
        Account account = accountService.findByAccountNumber(remitDto.getSenderAccountNumber());

        int exchangeRate = 0;
        if (remitDto.getTargetCurrency().equals("USD")){
            exchangeRate = 1100;
        }

        BigDecimal totalAmount = remitDto.getForeignAmount().multiply(BigDecimal.valueOf(exchangeRate+((long) (exchangeRate / 10) *(100-user.getPercentage())/100)));
        System.out.println(totalAmount);
        BigDecimal total;
        int compareTarget = remitDto.getForeignAmount().compareTo(BigDecimal.valueOf(5000));

        if (compareTarget < 0) {
            total = totalAmount.add(BigDecimal.valueOf(5000));
        } else {
            total = totalAmount.add(BigDecimal.valueOf(10000));
        }
        System.out.println(total);

        if (account.getBalance().compareTo(total)>=0){
            account.setBalance(account.getBalance().subtract(total));
        } else {
            throw new Exception("잔액부족");
        }

        accountService.save(account);
        
        Remit remit = Remit.builder()
                .targetCountry(remitDto.getTargetCountry())
                .targetCurrency(remitDto.getTargetCurrency())
                .foreignAmount(remitDto.getForeignAmount())
                .exchangeRate(exchangeRate)
                .totalAmount(total)
                .user(user)
                .senderAccountNumber(remitDto.getSenderAccountNumber())
                .receiverName(remitDto.getReceiverName())
                .receiverAccountNumber(remitDto.getReceiverAccountNumber())
                .receiverEmail(remitDto.getReceiverEmail())
                .receiverAddress(remitDto.getReceiverAddress())
                .build();
        remitService.save(remit);
    }
}
