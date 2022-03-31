package com.pjt.flowing.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SwotIdResponseDto {
    private Long swotId;
    public SwotIdResponseDto(Long swotId) {
        this.swotId = swotId;
    }
}
