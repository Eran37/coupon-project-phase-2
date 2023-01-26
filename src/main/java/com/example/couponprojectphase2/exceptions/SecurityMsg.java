package com.example.couponprojectphase2.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SecurityMsg {

    EMAIL_ALREADY_EXIST("email already exist", HttpStatus.CONFLICT), // err 409 conflict - already exists
    EMAIL_OR_NAME_ALREADY_EXIST("email or name already exist", HttpStatus.CONFLICT), // err 409 conflict - already exists
    EMAIL_OR_PASSWORD_INCORRECT("email or password incorrect", HttpStatus.UNAUTHORIZED), // err 401 unauthorized
    INVALID_TOKEN("invalid token please login again", HttpStatus.UNAUTHORIZED); //err 409 conflict - already exists


    private final String msg;
    private final HttpStatus status;

    SecurityMsg(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }
}
