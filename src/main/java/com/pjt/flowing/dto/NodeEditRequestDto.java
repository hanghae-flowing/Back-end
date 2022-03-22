package com.pjt.flowing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NodeEditRequestDto {
    private String xval;
    private String yval;
    private String width;
    private String height;
    private String text;
    private String radius;
}
