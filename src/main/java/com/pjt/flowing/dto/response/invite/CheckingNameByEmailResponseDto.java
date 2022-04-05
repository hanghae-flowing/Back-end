package com.pjt.flowing.dto.response.invite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CheckingNameByEmailResponseDto {
    private String nickname;
    private String image;
}
