package com.pjt.flowing.dto.response.gapnode;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class GapNodeResponseDto {
    private String subject;
    private String text;
    private String targetText;
    private Long gapTableId;
    private Long gapNodeId;

    @Builder
    public GapNodeResponseDto(String subject, String text, String targetText, Long gapTableId, Long gapNodeId) {
        this.subject = subject;
        this.text = text;
        this.targetText = targetText;
        this.gapTableId = gapTableId;
        this.gapNodeId=gapNodeId;
    }
}
