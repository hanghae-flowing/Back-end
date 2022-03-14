package com.pjt.flowing.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjt.flowing.dto.KakaoUserInfoDto;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class Authorization {

    //api 나왔을때 인가 부분 매크로로 만들자

    private String getKakaoId(String accessToken) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken); //카카오에서 공식적으로 해달라고 했다
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoUserInfoRequest, String.class);
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody); //objectmapper는 jsonnode 형태이다.

        Long id = jsonNode.get("id").asLong();
        System.out.println("카카오 Authorization");
        JSONObject obj = new JSONObject();
        return "asdf";
    }
}
