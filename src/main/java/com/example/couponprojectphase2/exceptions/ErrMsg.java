package com.example.couponprojectphase2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrMsg {

    ID_NOT_FOUND("ID NOT FOUND"),
    ID_ALREADY_EXIST("ID ALREADY EXIST"),
    COMPANY_EMAIL_EXIST("EMAIL ALREADY EXIST"),
    TITLE_ALREADY_EXIST("YOU ALREADY HAVE A COUPON WITH THIS TITLE"),
    EMAIL_OR_PASSWORD_INCORRECT("EMAIL OR PASSWORD INCORRECT"),
    NOT_AUTHORIZED("YOU ARE NOT AUTHORIZED");

    private final String msg;


}
