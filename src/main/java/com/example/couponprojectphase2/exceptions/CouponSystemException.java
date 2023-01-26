package com.example.couponprojectphase2.exceptions;


import java.util.function.Supplier;

public class CouponSystemException extends Exception implements Supplier<CouponSystemException> {

    public CouponSystemException(ErrMsg msg) {
        super(msg.getMsg());
    }

    @Override
    public CouponSystemException get() {
        return this;
    }
}
