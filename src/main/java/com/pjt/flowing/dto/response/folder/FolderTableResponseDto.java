package com.pjt.flowing.dto.response.folder;

import com.pjt.flowing.model.folder.FolderTable;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FolderTableResponseDto {
    private Long userId;
    private Long folderTableId;
    private String folderName;
    private LocalDateTime modifiedAt;
    private boolean trash;
    private boolean bookmark;
    public static FolderTableResponseDto myFolder(FolderTable folderTable){
        return FolderTableResponseDto.builder()
                .userId(folderTable.getMember().getId())
                .folderTableId(folderTable.getId())
                .folderName(folderTable.getFolderName())
                .modifiedAt(folderTable.getModifiedAt())
                .trash(folderTable.isTrash())
                .bookmark(folderTable.isBookmark())
                .build();
    }
}
