package com.pjt.flowing.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    public String kakaoLogout(HttpSession session){
        memberService.kakaoLogout((String)session.getAttribute("accessToken"));
        session.removeAttribute("accessToken");
        session.removeAttribute("userId");

        JSONObject obj = new JSONObject();
        obj.put("msg","logout");
        return obj.toString();
    }
}
