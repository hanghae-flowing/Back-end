package com.pjt.flowing.controller;

import com.pjt.flowing.dto.InviteRequestDto;
import com.pjt.flowing.dto.InviteResponseDto;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Controller
public class AlarmController {

    private final MemberRepository memberRepository;
    private final SimpMessageSendingOperations messagingTemplates;

    @MessageMapping("/invite/{email}")
    public InviteResponseDto inviteProject(@DestinationVariable("email") String email, @Payload InviteRequestDto requestDto) {

        InviteResponseDto inviteResponseDto = new InviteResponseDto();
        // 초대하려는 이메일이 존재하지 않을 때 !!
        System.out.println(requestDto.getEmail());
        if (!memberRepository.existsByEmail(requestDto.getEmail())) {
            inviteResponseDto.setMsg("초대하려는 이메일이 존재하지 않습니다.");
            System.out.println(memberRepository.existsByEmail(requestDto.getEmail()));
            return inviteResponseDto;
        }

        // 초대하려는 이메일이 존재할 때 !!
        Member receiver = memberRepository.findByEmail(requestDto.getEmail());

        // 프론트에게 전해줄 보내는 자의 Id와 보내고 싶은 사용자 Id를 담아서 전해준다.
        inviteResponseDto.setMsg("초대완료");
        inviteResponseDto.setSenderEmail(email);
        inviteResponseDto.setReceiverEmail(requestDto.getEmail());
        System.out.println(receiver.getId());

        messagingTemplates.convertAndSend("/sub/invite/" + receiver.getEmail(), inviteResponseDto);

        return inviteResponseDto;
    }
}
