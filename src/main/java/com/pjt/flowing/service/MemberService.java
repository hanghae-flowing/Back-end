package com.pjt.flowing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjt.flowing.dto.KakaoUserInfoDto;
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

import java.util.Optional;


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
        System.out.println("getaccessToken 실행후");

        // 2. 토큰으로 카카오 API 호출
        KakaoUserInfoDto kakaoUserInfoDto = getKakaoUserInfo(accessToken);
        System.out.println(("kakaouserinfodto 실행후"));

        // DB 에 중복된 Kakao Id 가 있는지 확인
        Long kakaoId = kakaoUserInfoDto.getId();
        String nickname = kakaoUserInfoDto.getNickname();
        String email = kakaoUserInfoDto.getEmail();
        String profileImageURL=kakaoUserInfoDto.getProfileImageURL();
        System.out.println(kakaoId);
        System.out.println("엑세스 토큰"+accessToken);
        JSONObject obj = new JSONObject();

        // 회원가입
        if (!memberRepository.findByKakaoId(kakaoId).isPresent()) {
            Member member = new Member(kakaoId,email,nickname,profileImageURL);
            memberRepository.save(member);
            System.out.println("이프문에 걸리니?");
        }
        else{
            System.out.println("엘스문에 걸리니?");
        }
        Optional<Member> member = memberRepository.findByKakaoId(kakaoId);

        obj.put("msg","true");
        obj.put("userId",member.get().getId());
        obj.put("kakaoId",kakaoId);
        obj.put("Email",email);
        obj.put("nickname",nickname);
        obj.put("accessToken",accessToken);
        obj.put("ProfileImageURL",profileImageURL);
        return obj.toString();
    }


    public String kakaoLogout(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoLogoutRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange("https://kapi.kakao.com/v1/user/logout", HttpMethod.POST, kakaoLogoutRequest, String.class);
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        System.out.println("로그아웃 id"+id);
        System.out.println("로그아웃 response"+response);

        JSONObject obj = new JSONObject();
        obj.put("msg","logout");
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
//        body.add("redirect_uri", "http://localhost:8080/member/kakao/callback");
//        body.add("redirect_uri", "http://localhost:3000/member/kakao/callback");
        body.add("redirect_uri", "http://hanghae-toaster.s3-website.ap-northeast-2.amazonaws.com/member/kakao/callback");
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

//    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
    public KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {

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
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties").get("nickname").asText();
        String email = jsonNode.get("kakao_account").get("email").asText();
        String profileImageURL = "";
        try {
            profileImageURL = jsonNode.get("properties").get("profile_image").asText();
        } catch (NullPointerException e) {
            System.out.println("없어");
        }
        System.out.println("카카오 api호출 response" + response);
        return new KakaoUserInfoDto(id, nickname, email, profileImageURL);
    }
}