package com.example.couponprojectphase2.exceptions;

import lombok.Data;

@Data
public class SecurityException extends Exception {

    private SecurityMsg securityMsg;

    public SecurityException(SecurityMsg securityMsg) {
        super(securityMsg.getMsg());
        this.securityMsg = securityMsg;
    }


}
