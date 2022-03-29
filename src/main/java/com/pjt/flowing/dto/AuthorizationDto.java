package com.pjt.flowing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AuthorizationDto {
    private String accessToken;
    private Long kakaoId;
    private Long userId;
}
