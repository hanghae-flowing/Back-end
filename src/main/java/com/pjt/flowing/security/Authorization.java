package com.pjt.flowing.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjt.flowing.dto.AuthorizationDto;
import lombok.NoArgsConstructor;
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
    public int getKakaoId(@RequestBody AuthorizationDto autorizationDto) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + autorizationDto.getACCESS_TOKEN());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoRequest, String.class);
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Long kakaoId = jsonNode.get("id").asLong();
        System.out.println("카카오 Authorization");

        int flag;
        if(kakaoId.equals(autorizationDto.getKakaoId()))
        {       //인가 허가시 flag =1, 정보 틀릴시 flag=0
            flag=1;
        }
        else{
            flag=0;
        }
        return flag;
    }

}
