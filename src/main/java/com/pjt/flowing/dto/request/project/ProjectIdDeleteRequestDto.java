package com.pjt.flowing.dto.request.project;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ProjectIdDeleteRequestDto {
    private List<Long> projectIdList;
}
