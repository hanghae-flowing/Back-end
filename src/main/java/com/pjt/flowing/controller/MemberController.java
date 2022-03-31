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

    @GetMapping("/member/kakao/callback")   //카카오 로그인
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        return memberService.kakaoLogin(code);
    }

    @PostMapping("/logout") //카카오 로그아웃
    public String kakaoLogout(@RequestBody String accessToken) throws JsonProcessingException{
        return memberService.kakaoLogout(accessToken);
    }

    @PostMapping("/mypage") //마이페이지 정보 보내주기
    public KakaoUserInfoDto myInfo(@RequestBody AuthorizationDto dto) throws JsonProcessingException {
        return memberService.getKakaoUserInfo(dto.getAccessToken());
    }
}
