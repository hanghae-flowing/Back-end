package com.pjt.flowing.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FolderAddProjectRequestDto {
    Long folderTableId;
    Long projectId;
}
