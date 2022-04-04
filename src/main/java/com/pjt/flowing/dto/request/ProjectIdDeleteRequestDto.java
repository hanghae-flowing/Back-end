package com.pjt.flowing.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ProjectIdDeleteRequestDto {

    private List<Long> projectIdList;
}
