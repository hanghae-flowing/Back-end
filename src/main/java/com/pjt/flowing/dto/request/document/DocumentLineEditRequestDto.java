package com.pjt.flowing.dto.request.document;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DocumentLineEditRequestDto {
    private String text;
    private int weight;
    private int fontSize;
    private String color;
    private int indexNum;
}
