package com.pjt.flowing.dto.response.gapnode;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GapTableIdResponseDto {
    private Long gapTableId;
    public GapTableIdResponseDto(Long gapTableId) {
        this.gapTableId = gapTableId;
    }
}
