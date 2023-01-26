package com.example.couponprojectphase2.controllers;

import com.example.couponprojectphase2.beans.Category;
import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.dtos.CouponDto;
import com.example.couponprojectphase2.exceptions.CouponSystemException;
import com.example.couponprojectphase2.exceptions.ErrMsg;
import com.example.couponprojectphase2.exceptions.SecurityException;
import com.example.couponprojectphase2.mappers.CouponMapper;
import com.example.couponprojectphase2.security.ClientType;
import com.example.couponprojectphase2.security.TokenManager;
import com.example.couponprojectphase2.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customers")
@CrossOrigin(origins = "http://localhost:3000/api/customer")
public class CustomerController {

    private final CustomerService service;
    private final TokenManager tokenManager;
    private final CouponMapper couponMapper;

    @PostMapping("{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.CUSTOMER))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        service.buyCoupon(tokenManager.getUserId(token), couponId);
    }

    @GetMapping("coupons")
    public List<CouponDto> getAllCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.CUSTOMER))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return couponMapper.toDtoList(service.getAllCoupons(tokenManager.getUserId(token)));
    }

    @GetMapping("couponsByCategory")
    public List<CouponDto> getAllCouponsByCategory(@RequestHeader("Authorization") UUID token, Category category) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.CUSTOMER))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return couponMapper.toDtoList(service.getCouponsByCategory(tokenManager.getUserId(token), category));
    }

    @GetMapping("couponsByMaxPrice")
    public List<CouponDto> getAllCouponsByMaxPrice(@RequestHeader("Authorization") UUID token, int max) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.CUSTOMER))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return couponMapper.toDtoList(service.getCouponsByMaxPrice(tokenManager.getUserId(token), max));
    }

    @GetMapping("details")
    public Customer getDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.CUSTOMER))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return service.getCustomerDetails(tokenManager.getUserId(token));
    }



}
