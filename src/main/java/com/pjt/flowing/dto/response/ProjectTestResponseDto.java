package com.pjt.flowing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProjectTestResponseDto {
    private Long projectId;
    private String projectName;
    private LocalDateTime modifiedAt;
    private List<String> memberList;
    private int thumbnailNum;
    private boolean trash;
    private boolean bookmark;
}
