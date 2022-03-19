package com.pjt.flowing.dto;

import com.pjt.flowing.model.Bookmark;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.model.ProjectMember;
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
    public static ProjectResponseDto from2(Bookmark bookmark){
        return ProjectResponseDto.builder()
                .projectId(bookmark.getProject().getId())
                .projectName(bookmark.getProject().getProjectName())
                .modifiedAt(bookmark.getProject().getModifiedAt())
                //.memberList(project.getProjectMemberList())
                //.bookmark(project.getBookmarkList())
                .thumbnailNum(bookmark.getProject().getThumbNailNum())
                .build();
    }

    public static ProjectResponseDto includedProject(ProjectMember projectMember){
        return ProjectResponseDto.builder()
                .projectId(projectMember.getProject().getId())
                .projectName(projectMember.getProject().getProjectName())
                .modifiedAt(projectMember.getProject().getModifiedAt())
                //.memberList(project.getProjectMemberList())
                //.bookmark(project.getBookmarkList())
                .thumbnailNum(projectMember.getProject().getThumbNailNum())
                .build();
    }
}
