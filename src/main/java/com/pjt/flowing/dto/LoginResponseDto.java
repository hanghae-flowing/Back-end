package com.pjt.flowing.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String nickname;
    private String Email;
    private String ACCESS_TOKEN;
    private Long kakaoId;

    @Builder
    public LoginResponseDto(String nickname, String Email, String ACCESS_TOKEN,Long kakaoId){
        this.nickname=nickname;
        this.Email=Email;
        this.ACCESS_TOKEN=ACCESS_TOKEN;
        this.kakaoId=kakaoId;
    }
}
