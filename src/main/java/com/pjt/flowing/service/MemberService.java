package com.pjt.flowing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjt.flowing.dto.KakaoUserInfoDto;
import com.pjt.flowing.dto.LoginResponseDto;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;



@Service
public class MemberService {

    @Value("${KAKAO_REST_API}") //api값은 밖으로 보여주면 안돼
    private String kakao_api;

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public String kakaoLogin(String code) throws JsonProcessingException {

        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);

        // 2. 토큰으로 카카오 API 호출
        KakaoUserInfoDto kakaoUserInfoDto = getKakaoUserInfo(accessToken);

        // DB 에 중복된 Kakao Id 가 있는지 확인
        Long kakaoId = kakaoUserInfoDto.getId();
        System.out.println(kakaoId);
        System.out.println("엑세스 토큰"+accessToken);
        JSONObject obj = new JSONObject();

        // 회원가입 //time stamp 고칠것
        if (!memberRepository.findByKakaoId(kakaoId).isPresent()) {
            String nickname = kakaoUserInfoDto.getNickname();
            String email = kakaoUserInfoDto.getEmail();
            Member member = new Member(kakaoId,email,nickname);
            memberRepository.save(member);
            obj.put("msg","signup ok");
            System.out.println("이프문에 걸리니?");
        }
        else{
            System.out.println("엘스문에 걸리니?");
            obj.put("msg","already exist");
        }

        obj.put("msg","login ok");
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .kakaoId(getKakaoUserInfo(accessToken).getId())
                .ACCESS_TOKEN(accessToken)
                .nickname(getKakaoUserInfo(accessToken).getNickname())
                .Email(getKakaoUserInfo(accessToken).getEmail())
                .build();

        String mail = loginResponseDto.getEmail();
        Long kakaoid = loginResponseDto.getKakaoId();
        String name = loginResponseDto.getNickname();
        String token = loginResponseDto.getACCESS_TOKEN();

        obj.put("kakaoId",kakaoid);
        obj.put("Email",mail);
        obj.put("nickname",name);
        obj.put("ACCESS_TOKEN",token);
        //여기서 프론트에 userinfo를 response로 줘야하나?
        return obj.toString();
    }

    private String getAccessToken(String code) throws JsonProcessingException { //code = 인가코드
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakao_api);
        body.add("redirect_uri", "http://localhost:8080/member/kakao/callback");
//        body.add("redirect_uri", "http://localhost:3000/member/kakao/callback");
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();       // NEED to STUDY
        ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token",
                HttpMethod.POST, kakaoTokenRequest, String.class);
        System.out.println("토큰정보"+response);

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();   //엑세스 토큰 카카오로 부터 받아옴
    }

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {

        //모든 요청에 이걸 넣어야 되나?를 고민 해봐야 할듯. write only?
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken); //카카오에서 공식적으로 해달라고 했다
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        //토큰을 url로 보내주면 reponse를 주게 된다.
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoUserInfoRequest, String.class);
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody); //objectmapper는 jsonnode 형태이다.

        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties").get("nickname").asText();
        String email = jsonNode.get("kakao_account").get("email").asText();
        System.out.println("카카오 사용자 정보: " + id + ", " + nickname + ", " + email);
        System.out.println("카카오 api호출 response"+response);
        return new KakaoUserInfoDto(id,nickname,email);
    }
}
