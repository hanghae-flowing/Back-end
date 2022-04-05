package com.pjt.flowing.dto.request.folder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FolderCreateRequestDto {
    private String folderName;
    private Long userId;
}
