package com.pettalk.pettalkbackend.dto.auth;

import lombok.Data;

@Data
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
