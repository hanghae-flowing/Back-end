package com.pjt.flowing.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class DocumentLineResponseDto {
    private Long lineId;
    private String text;
    private int weight;
    private int fontSize;
    private String color;
    private int indexNum;

    @Builder
    public DocumentLineResponseDto(Long lineId, String text, int weight,
                                   int fontSize, String color,int indexNum) {
        this.lineId = lineId;
        this.text = text;
        this.weight = weight;
        this.fontSize = fontSize;
        this.color = color;
        this.indexNum = indexNum;
    }
}
