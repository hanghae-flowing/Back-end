package com.pjt.flowing.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NodeResponseDto {
    private String xval;
    private String yval;
    private String width;
    private String height;
    private String text;
    private String radius;
    private int isChecked;
    private Long nodeTableId;
    private Long nodeId;

    @Builder
    public NodeResponseDto(String xval, String yval, String width, String height, String text,
                           String radius, int isChecked, Long nodeTableId, Long nodeId) {
        this.xval = xval;
        this.yval = yval;
        this.width = width;
        this.height = height;
        this.text = text;
        this.radius = radius;
        this.isChecked = isChecked;
        this.nodeTableId = nodeTableId;
        this.nodeId=nodeId;
    }
}
