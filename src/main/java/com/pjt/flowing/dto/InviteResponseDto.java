package com.pjt.flowing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class InviteResponseDto {

    private String msg;
    private String senderEmail;
    private String receiverEmail;
}
