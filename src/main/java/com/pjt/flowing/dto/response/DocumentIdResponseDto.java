package com.pjt.flowing.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DocumentIdResponseDto {
    private Long documentId;
    public DocumentIdResponseDto(Long documentId) {
        this.documentId = documentId;
    }
}
