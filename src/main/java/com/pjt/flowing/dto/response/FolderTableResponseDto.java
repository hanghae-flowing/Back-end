package com.pjt.flowing.dto.response;

import com.pjt.flowing.model.FolderTable;
import com.pjt.flowing.model.ProjectMember;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class FolderTableResponseDto {
    private Long userId;
    private Long folderTableId;
    private String folderName;
    private LocalDateTime modifiedAt;
    private boolean trash;

    public static FolderTableResponseDto myFolder(FolderTable folderTable){

        return FolderTableResponseDto.builder()
                .userId(folderTable.getMember().getId())
                .folderTableId(folderTable.getId())
                .folderName(folderTable.getFolderName())
                .modifiedAt(folderTable.getModifiedAt())
                .trash(folderTable.isTrash())
                .build();


    }
}
