package com.pjt.flowing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InviteRequestDto {

    private Long projectId;
    private String senderEmail;
    private String receiverEmail;
}
