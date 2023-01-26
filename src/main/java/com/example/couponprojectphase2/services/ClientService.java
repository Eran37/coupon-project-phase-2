package com.example.couponprojectphase2.services;

import com.example.couponprojectphase2.exceptions.CouponSystemException;
import com.example.couponprojectphase2.repositories.CompanyRepository;
import com.example.couponprojectphase2.repositories.CouponRepository;
import com.example.couponprojectphase2.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class ClientService {

//    @Autowired
    protected CouponRepository couponRepository;
//    @Autowired
    protected CustomerRepository customerRepository;
//    @Autowired
    protected CompanyRepository companyRepository;

//    public abstract boolean login(String email, String password) throws CouponSystemException;

}
