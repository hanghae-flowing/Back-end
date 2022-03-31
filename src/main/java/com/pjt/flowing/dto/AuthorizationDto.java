package com.pjt.flowing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizationDto {
    private String accessToken;
    private Long kakaoId;
    private Long userId;
}
