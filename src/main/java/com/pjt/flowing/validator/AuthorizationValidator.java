package com.pjt.flowing.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.AuthorizationDto;
import com.pjt.flowing.exception.BadRequestException;
import com.pjt.flowing.exception.ErrorCode;
import com.pjt.flowing.security.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component // 빈으로 만들어야 사용가능!!
public class AuthorizationValidator {

    private final Authorization authorization;

    public void tokenCheck(AuthorizationDto authorizationDto) throws JsonProcessingException {
        if (authorization.getKakaoId(authorizationDto) == 0){
            throw new BadRequestException(ErrorCode.USER_NOT_PURMITTED);
        }
    }
}
