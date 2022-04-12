package com.pjt.flowing.dto.response.project;

import lombok.Getter;

@Getter
public class ProjectMemberResponseDto {
    private Long userId;
    private String nickName;
    private String profileImageUrl;

    public ProjectMemberResponseDto(Long userId, String nickName, String profileImageUrl) {
        this.userId = userId;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }
}
