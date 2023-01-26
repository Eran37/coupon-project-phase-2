package com.example.couponprojectphase2.dtos;
import com.example.couponprojectphase2.beans.Coupon;
import com.example.couponprojectphase2.security.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResDto {

    private int id;
    private String email;
    private UUID token;
    private ClientType clientType;
    private List<CouponDto> coupons;

}
