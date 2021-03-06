package com.pjt.flowing.dto.response.node;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NodeResponseDto {
    private int xval;
    private int yval;
    private String width;
    private String height;
    private String text;
    private String radius;
    private int isChecked;
    private Long nodeTableId;
    private Long nodeId;

    @Builder
    public NodeResponseDto(int xval, int yval, String width, String height, String text,
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
