package com.pjt.flowing.dto.request.folder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FolderGetRequestDto {
    private Long folderTableId;
    private Long userId;
}
