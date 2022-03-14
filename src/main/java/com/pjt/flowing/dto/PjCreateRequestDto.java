package com.pjt.flowing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class PjCreateRequestDto {
    private String projectName;
    private int thumbNailNum;
    private Long objectId;
    private Long kakaoId;
    private String accessToken;
}
