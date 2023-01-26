package com.example.couponprojectphase2.services;

import com.example.couponprojectphase2.beans.Category;
import com.example.couponprojectphase2.beans.Coupon;
import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {

    boolean login(String email, String password) throws CouponSystemException;

    void buyCoupon(int customerId, int couponId) throws CouponSystemException;

    List<Coupon> getAllCoupons(int customerId);

    List<Coupon> getCouponsByCategory(int customerId, Category category);

    List<Coupon> getCouponsByMaxPrice(int customerId, int maxPrice);

    Customer getCustomerDetails(int customerId) throws CouponSystemException;


}
