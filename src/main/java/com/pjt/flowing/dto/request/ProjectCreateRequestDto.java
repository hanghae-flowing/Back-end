package com.pjt.flowing.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectCreateRequestDto {
    private String projectName;
    private int thumbNailNum;
    private Long objectId;
    private Long userId;
    private Long kakaoId;
    private String accessToken;
}
