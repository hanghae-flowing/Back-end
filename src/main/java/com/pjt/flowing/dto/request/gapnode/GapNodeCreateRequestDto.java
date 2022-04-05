package com.pjt.flowing.dto.request.gapnode;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GapNodeCreateRequestDto {
    private String subject;
    private String text;
    private String targetText;
    private Long gapTableId;
}
