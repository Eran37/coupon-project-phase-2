package com.example.couponprojectphase2.controllers;

import com.example.couponprojectphase2.beans.Category;
import com.example.couponprojectphase2.beans.Coupon;
import com.example.couponprojectphase2.dtos.CompanyDto;
import com.example.couponprojectphase2.dtos.CouponDto;
import com.example.couponprojectphase2.exceptions.CouponSystemException;
import com.example.couponprojectphase2.exceptions.ErrMsg;
import com.example.couponprojectphase2.exceptions.SecurityException;
import com.example.couponprojectphase2.mappers.CouponMapper;
import com.example.couponprojectphase2.security.ClientType;
import com.example.couponprojectphase2.security.TokenManager;
import com.example.couponprojectphase2.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/companies")
@CrossOrigin(origins = "http://localhost:3000/api/company")
public class CompanyController {

    private final CompanyService service;
    private final TokenManager tokenManager;
    private final CouponMapper couponMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@RequestHeader("Authorization")UUID token, @Valid @RequestBody CouponDto dto) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.COMPANY))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        service.addCoupon(tokenManager.getUserId(token), dto);
    }

    @PutMapping("{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int couponId, @RequestHeader("Authorization") UUID token, @RequestBody CouponDto dto) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.COMPANY))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        service.updateCoupon(tokenManager.getUserId(token), couponId, dto);
    }

    @DeleteMapping("{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId ) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.COMPANY))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        service.deleteCoupon(tokenManager.getUserId(token), couponId );
    }

    @GetMapping("coupons")
    public List<CouponDto> getAllCoupons(@RequestHeader("Authorization") UUID token) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.COMPANY))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return couponMapper.toDtoList(service.getAllCoupons(tokenManager.getUserId(token)));
    }

    @GetMapping("couponsByCategory")
    public List<CouponDto> getCouponsByCategory (@RequestHeader("Authorization") UUID token, Category category) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.COMPANY))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return service.getAllCouponsByCategory(tokenManager.getUserId(token), category);
    }

    @GetMapping("couponsByMaxPrice")
    public List<CouponDto> getCouponsByMaxPrice (@RequestHeader("Authorization") UUID token, int max) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.COMPANY))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return service.getAllCouponsByMaxPrice(tokenManager.getUserId(token), max);
    }

    @GetMapping("details")
    public CompanyDto companyDetails(@RequestHeader("Authorization") UUID token) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.COMPANY))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return service.getCompanyDetails(tokenManager.getUserId(token));
    }


}
