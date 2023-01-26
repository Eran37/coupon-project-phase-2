package com.example.couponprojectphase2.exceptions;

public class BusinessLogicException extends Exception {

    public BusinessLogicException(ErrMsg msg) {
        super(msg.getMsg());
    }

}
