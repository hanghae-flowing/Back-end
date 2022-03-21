package com.pjt.flowing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NodePinRequestDto {
    private Long projectId;
    private Long nodeId;
}
