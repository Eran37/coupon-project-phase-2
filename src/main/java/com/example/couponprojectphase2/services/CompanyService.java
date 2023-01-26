package com.example.couponprojectphase2.services;

import com.example.couponprojectphase2.beans.Category;
import com.example.couponprojectphase2.beans.Coupon;
import com.example.couponprojectphase2.dtos.CompanyDto;
import com.example.couponprojectphase2.dtos.CouponDto;
import com.example.couponprojectphase2.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {

    boolean login(String email, String password) throws CouponSystemException;

    void addCoupon(int companyId, CouponDto dto) throws CouponSystemException;

    void updateCoupon(int companyId, int couponId, CouponDto dto) throws CouponSystemException;

    void deleteCoupon(int companyId, int couponId) throws CouponSystemException;

    List<Coupon> getAllCoupons(int companyId);

    List<CouponDto> getAllCouponsByCategory(int companyId, Category category);

    List<CouponDto> getAllCouponsByMaxPrice(int companyId, double price);

    CompanyDto getCompanyDetails(int companyId) throws CouponSystemException;


}
