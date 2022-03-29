package com.pjt.flowing.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectEditRequestDto {
    private String projectName;
    private int thumbNailNum;
    private Long userId;
}
