package com.pjt.flowing.dto.request.node;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NodePathRequestDto {
    private Long nodeTableId;
    private Long parentNode;
    private Long childNode;
}
