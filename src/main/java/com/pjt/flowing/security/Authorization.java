package com.pjt.flowing.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjt.flowing.dto.AuthorizationDto;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@Service
public class Authorization {

    private String getKakaoId(@RequestBody AuthorizationDto autorizationDto) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + autorizationDto.getAccessToken());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoRequest, String.class);
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Long kakaoId = jsonNode.get("id").asLong();
        System.out.println("카카오 Authorization");
        JSONObject obj = new JSONObject();

        if(kakaoId.equals(autorizationDto.getKakaoId()))
        {
            obj.put("msg","정보가 일치합니다");
        }
        else{
            obj.put("msg","kakaoId가 다르거나 유효하지 않은 토큰입니다.");
        }
        return obj.toString();
    }
}
