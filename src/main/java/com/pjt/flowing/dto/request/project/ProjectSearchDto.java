package com.pjt.flowing.dto.request.project;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectSearchDto {
    private Long userId;
    private String text;
}
