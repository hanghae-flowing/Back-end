package com.pjt.flowing.dto.response.invite;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class InviteResponseDto {

    private Long invitingId;
    private String inviting;
    private String projectName;
    private LocalDateTime modifiedAt;
    private String image;

    public InviteResponseDto(Long invitingId, String inviting, String projectName,LocalDateTime modifiedAt, String image){
        this.invitingId = invitingId;
        this.inviting=inviting;
        this.projectName = projectName;
        this.modifiedAt = modifiedAt;
        this.image = image;
    }
}
