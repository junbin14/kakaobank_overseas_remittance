package com.project.kakaobank.controller;

import com.project.kakaobank.domain.RecentReceiverInfoDto;
import com.project.kakaobank.domain.User;
import com.project.kakaobank.domain.UserDto;
import com.project.kakaobank.service.RemitService;
import com.project.kakaobank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final RemitService remitService;
    @Autowired
    public UserController(UserService userService, RemitService remitService) {
        this.userService = userService;
        this.remitService = remitService;
    }

    @PostMapping("/user/create")
    @ResponseBody
    public void create(@RequestBody UserDto userDto){
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .address(userDto.getAddress())
                .percentage(userDto.getPercentage())
                .fileUploadYN("N")
                .build();
        userService.save(user);
    }

    @GetMapping("recent_receiver_info")
    @ResponseBody
    public List<RecentReceiverInfoDto> getRecentReceiverInfo(@RequestParam(value = "userId")Long userId){
        return remitService.findRecentReceiverInfo(userId);

    }
}
