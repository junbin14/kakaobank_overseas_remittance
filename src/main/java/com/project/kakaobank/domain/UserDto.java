package com.project.kakaobank.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    private String name;
    private String email;
    private String password;
    private String address;
    private int percentage;
}
