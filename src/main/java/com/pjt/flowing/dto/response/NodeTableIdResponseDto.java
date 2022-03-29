package com.pjt.flowing.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NodeTableIdResponseDto {
    private Long nodeTableId;

    public NodeTableIdResponseDto(Long nodeTableId) {
        this.nodeTableId = nodeTableId;
    }
}
