package com.pjt.flowing.dto.response.project;

import com.pjt.flowing.model.project.Bookmark;
import com.pjt.flowing.model.project.Project;
import com.pjt.flowing.model.project.ProjectMember;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ProjectResponseDto {
    private Long projectId;
    private String projectName;
    private LocalDateTime modifiedAt;
    private List<String> memberList;
    private int thumbnailNum;
    private boolean trash;


    public static ProjectResponseDto from(Project project){
        List<String> nicknames = new ArrayList<>();
        project.getProjectMemberList().stream()
                .map(c -> c.getMember().getNickname())
                .forEach(s->nicknames.add(s));

        return ProjectResponseDto.builder()
                .projectId(project.getId())
                .projectName(project.getProjectName())
                .modifiedAt(project.getModifiedAt())
                .memberList(nicknames)
                .thumbnailNum(project.getThumbNailNum())
                .trash(project.isTrash())
                .build();
    }
    public static ProjectResponseDto from2(Bookmark bookmark){
        List<String> nicknames = new ArrayList<>();
        bookmark.getProject().getProjectMemberList().stream()
                .map(c -> c.getMember().getNickname())
                .forEach(s->nicknames.add(s));
        
        return ProjectResponseDto.builder()
                .projectId(bookmark.getProject().getId())
                .projectName(bookmark.getProject().getProjectName())
                .modifiedAt(bookmark.getProject().getModifiedAt())
                .memberList(nicknames)
                .thumbnailNum(bookmark.getProject().getThumbNailNum())
                .build();
    }

    public static ProjectResponseDto includedProject(ProjectMember projectMember){
        List<String> nicknames = new ArrayList<>();
        projectMember.getProject().getProjectMemberList().stream()
                .map(c -> c.getMember().getNickname())
                .forEach(s->nicknames.add(s));

            return ProjectResponseDto.builder()
                    .projectId(projectMember.getProject().getId())
                    .projectName(projectMember.getProject().getProjectName())
                    .modifiedAt(projectMember.getProject().getModifiedAt())
                    .memberList(nicknames)
                    .thumbnailNum(projectMember.getProject().getThumbNailNum())
                    .trash(projectMember.getProject().isTrash())
                    .build();
    }


}
