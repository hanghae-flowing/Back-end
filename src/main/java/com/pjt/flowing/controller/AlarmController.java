package com.pjt.flowing.controller;

import com.pjt.flowing.dto.InviteRequestDto;
import com.pjt.flowing.dto.InviteResponseDto;
import com.pjt.flowing.dto.MsgResponseDto;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AlarmController {

    private final MemberRepository memberRepository;
    private final SimpMessageSendingOperations messagingTemplates;

    @PostMapping("/invite")
    public MsgResponseDto inviteProject(@RequestBody InviteRequestDto requestDto) {

        MsgResponseDto msgResponseDto = new MsgResponseDto();
        // 초대하려는 이메일이 존재하지 않을 때 !!
        if (!memberRepository.existsByEmail(requestDto.getEmail())) {
            msgResponseDto.setMsg("초대하려는 이메일이 존재하지 않습니다.");
            return msgResponseDto;
        }

        // 초대하려는 이메일이 존재할 때 !!
        Member sender = memberRepository.findById(requestDto.getSenderId()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Member receiver = memberRepository.findByEmail(requestDto.getEmail());

        // 프론트에게 전해줄 보내는 자의 Id와 보내고 싶은 사용자 Id를 담아서 전해준다.
        InviteResponseDto inviteResponseDto = new InviteResponseDto();
        inviteResponseDto.setSenderId(sender.getId());
        inviteResponseDto.setReceiverid(receiver.getId());

        messagingTemplates.convertAndSend("/sub/invite" + receiver.getId(), inviteResponseDto);
        msgResponseDto.setMsg("초대완료");

        return msgResponseDto;
    }
}
