package com.pjt.flowing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizationDto {
    private Long kakaoId;
    private String ACCESS_TOKEN;
}
