package com.pjt.flowing.dto.request.node;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class NodeCreateRequestDto {
    private int xval;
    private int yval;
    private String width;
    private String height;
    private String text;
    private String radius;
    private int isChecked;
    private Long nodeTableId;

}
