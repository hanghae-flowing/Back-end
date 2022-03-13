package com.pjt.flowing.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        System.out.println("인가 코드"+code);
        return memberService.kakaoLogin(code);
    }

    @PostMapping("/api/logout")
    public String kakaoLogout(@RequestParam String accessToken) throws JsonProcessingException{
        System.out.println("엑세스 토큰"+accessToken);
        return memberService.kakaoLogout(accessToken);
    }

}
