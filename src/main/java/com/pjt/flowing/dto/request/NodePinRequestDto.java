package com.pjt.flowing.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NodePinRequestDto {
    private Long projectId;
    private Long nodeId;
}
