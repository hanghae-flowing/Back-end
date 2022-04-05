package com.pjt.flowing.dto.request.invite;


import lombok.Getter;

@Getter
public class AcceptRequestDto {
    private Long userId;    //초대 받는 사람의 id
    private Long projectId; //초대하는 프로젝트id
}
