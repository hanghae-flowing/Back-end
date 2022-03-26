package com.pjt.flowing.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DocumentLineEditRequestDto {
    private String text;
    private int weight;
    private int fontsize;
    private String color;
    private int indexNum;
}
