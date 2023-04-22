package com.project.kakaobank.controller;

import com.project.kakaobank.domain.User;
import com.project.kakaobank.domain.UserDto;
import com.project.kakaobank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
}
