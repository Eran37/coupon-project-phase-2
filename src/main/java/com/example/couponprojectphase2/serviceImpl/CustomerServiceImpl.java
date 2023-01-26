package com.example.couponprojectphase2.serviceImpl;

import com.example.couponprojectphase2.beans.Category;
import com.example.couponprojectphase2.beans.Coupon;
import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.exceptions.CouponSystemException;
import com.example.couponprojectphase2.exceptions.ErrMsg;
import com.example.couponprojectphase2.mappers.CouponMapper;
import com.example.couponprojectphase2.security.TokenManager;
import com.example.couponprojectphase2.services.ClientService;
import com.example.couponprojectphase2.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private final CouponMapper couponMapper;
    private final TokenManager tokenManager;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!customerRepository.existsByEmailAndPassword(email, password))
            throw new CouponSystemException(ErrMsg.EMAIL_OR_PASSWORD_INCORRECT);
        return true;
    }

    @Override
    public void buyCoupon(int customerId, int couponId) throws CouponSystemException {
        if (!customerRepository.existsById(customerId) || couponRepository.existsById(couponId))
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        Customer customer = customerRepository.getById(customerId);
        Coupon coupon = couponRepository.getById(couponId);
        if (!purchaseValidation(customer, coupon))
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        customer.getCoupons().add(coupon);
        customerRepository.saveAndFlush(customer);
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepository.saveAndFlush(coupon);
    }

    public boolean purchaseValidation(Customer customer, Coupon coupon) {
        return coupon.getAmount() > 0
                && (!customer.getCoupons().contains(coupon))
                && coupon.getEndDate().after(Date.valueOf(LocalDate.now()));
    }


    @Override
    public List<Coupon> getAllCoupons(int customerId) {
        return customerRepository.getById(customerId).getCoupons();
    }

    @Override
    public List<Coupon> getCouponsByCategory(int customerId, Category category) {
        List<Coupon> coupons = new ArrayList<>();
        getAllCoupons(customerId).forEach(coupon -> {
            if (coupon.getCategory().equals(category)) {
                coupons.add(coupon);
            }
        });
        return coupons;
    }

    @Override
    public List<Coupon> getCouponsByMaxPrice(int customerId, int maxPrice) {
        List<Coupon> coupons = new ArrayList<>();
        getAllCoupons(customerId).forEach(coupon -> {
            if (coupon.getPrice() <= maxPrice) {
                coupons.add(coupon);
            }
        });
        return coupons;
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CouponSystemException {
        if (!customerRepository.existsById(customerId))
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        return customerRepository.getById(customerId);
    }
}
