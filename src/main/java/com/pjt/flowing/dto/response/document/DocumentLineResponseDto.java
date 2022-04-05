package com.pjt.flowing.dto.response.document;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Getter
public class DocumentLineResponseDto {
    private Long lineId;
    private String text;
    private int weight;
    private int fontSize;
    private String color;
    private int indexNum;
    private Long documentId;
    private int maxLength;

    @Builder
    public DocumentLineResponseDto(Long lineId, String text, int weight,
                                   int fontSize, String color,int indexNum,Long documentId, int maxLength) {
        this.lineId = lineId;
        this.text = text;
        this.weight = weight;
        this.fontSize = fontSize;
        this.color = color;
        this.indexNum = indexNum;
        this.documentId = documentId;
        this.maxLength=maxLength;
    }
}
