package com.pjt.flowing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GapStoneResponseDto {

    private int xval;

    private String text;

    private Long gapNodeId;

    private Long gapStoneId;

    @Builder
    public GapStoneResponseDto(int xval, String text, Long gapNodeId, Long gapStoneId) {
        this.xval = xval;
        this.text = text;
        this.gapNodeId = gapNodeId;
        this.gapStoneId = gapStoneId;
    }
}
