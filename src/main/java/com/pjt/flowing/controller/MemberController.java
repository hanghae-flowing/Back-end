package com.pjt.flowing.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.AuthorizationDto;
import com.pjt.flowing.dto.KakaoUserInfoDto;
import com.pjt.flowing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/logout")
    public String kakaoLogout(@RequestBody String accessToken) throws JsonProcessingException{
        System.out.println("엑세스 토큰"+accessToken);
        return memberService.kakaoLogout(accessToken);
    }

    @PostMapping("/mypage")
    public KakaoUserInfoDto myInfo(@RequestBody AuthorizationDto dto) throws JsonProcessingException {
        return memberService.getKakaoUserInfo(dto.getAccessToken());
    }
}
