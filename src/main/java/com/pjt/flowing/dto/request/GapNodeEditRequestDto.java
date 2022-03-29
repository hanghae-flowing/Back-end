package com.pjt.flowing.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GapNodeEditRequestDto {
    private String subject;
    private String text;
    private String targetText;
}
