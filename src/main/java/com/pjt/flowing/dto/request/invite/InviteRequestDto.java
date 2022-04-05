package com.pjt.flowing.dto.request.invite;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class InviteRequestDto {
    private final Long projectId; //초대하는 프로젝트
    private final Long userId;//초대하는사람
    private final String email;//초대 받는 사람 이메일

}
