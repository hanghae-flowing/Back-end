package com.pjt.flowing.controller;

import com.pjt.flowing.dto.request.invite.InviteRequestDto;
import com.pjt.flowing.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class InviteController {
    private final InviteService inviteService;
    //초대하기.
    @PostMapping("/inviting")
    public String inviteMember(@RequestBody InviteRequestDto inviteRequestDto){
        return inviteService.inviteMember(inviteRequestDto);
    }

    //초대받은 목록 확인하기.
    @GetMapping("/inviting/{userId}")
    public String getInviting(@PathVariable Long userId){
        return inviteService.getInviting(userId);
    }

    //초대 수락하기.
    @DeleteMapping("/accepting/{invitingId}")
    public String acceptInviting(@PathVariable Long invitingId){
        return inviteService.accept(invitingId);
    }

    //초대 거절하기
    @DeleteMapping("/refusing/{invitingId}")
    public String refuseInviting(@PathVariable Long invitingId){
        return inviteService.refuse(invitingId);
    }

}
