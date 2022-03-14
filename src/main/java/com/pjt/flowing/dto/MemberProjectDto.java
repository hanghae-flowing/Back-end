package com.pjt.flowing.dto;

import lombok.Getter;

@Getter
public class MemberProjectDto {
    private final String nickName;
    public MemberProjectDto(String nickName){
        this.nickName=nickName;
    }
}
