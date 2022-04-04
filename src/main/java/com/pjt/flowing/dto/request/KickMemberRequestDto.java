package com.pjt.flowing.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class KickMemberRequestDto {

    private Long userId;

    private Long memberId;

    private Long projectId;
}
