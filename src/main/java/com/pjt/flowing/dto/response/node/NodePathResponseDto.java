package com.pjt.flowing.dto.response.node;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NodePathResponseDto {
    private Long parentNode;
    private Long childNode;

    public NodePathResponseDto(Long parentNode, Long childNode) {
        this.parentNode = parentNode;
        this.childNode = childNode;
    }
}
