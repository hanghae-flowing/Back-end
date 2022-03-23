package com.pjt.flowing.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class NodeCreateRequestDto {
    private String xval;
    private String yval;
    private String width;
    private String height;
    private String text;
    private String radius;
    private int isChecked;  //chap2~3에서 키워드 보여주려면 필요함
    private Long projectId;

}
