package com.pjt.flowing.dto;

import com.pjt.flowing.model.Project;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ProjectResponseDto {
    private Long projectId;
    private String projectName;
    private LocalDateTime modifiedAt;
    //private List<MemberProjectDto> memberList;
    //private boolean bookmark;
    private int thumbnailNum;

    public static ProjectResponseDto from(Project project){
        return ProjectResponseDto.builder()
                .projectId(project.getId())
                .projectName(project.getProjectName())
                .modifiedAt(project.getModifiedAt())
                //.memberList(project.getProjectMemberList())
                //.bookmark(project.getBookmarkList())
                .thumbnailNum(project.getThumbNailNum())
                .build();
    }
}
