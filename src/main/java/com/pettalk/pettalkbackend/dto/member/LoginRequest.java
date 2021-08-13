package com.pettalk.pettalkbackend.dto.member;

import lombok.Data;

@Data
public class LoginRequest {
    private String loginId;
    private String password;
}
