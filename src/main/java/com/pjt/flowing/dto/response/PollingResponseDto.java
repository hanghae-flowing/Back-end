package com.pjt.flowing.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PollingResponseDto {
    private Long textId;
    private String text;
    public PollingResponseDto(Long textId, String text){
        this.textId=textId;
        this.text=text;
    }
}
