package com.example.couponprojectphase2.services;

import com.example.couponprojectphase2.dtos.CompanyRegDto;
import com.example.couponprojectphase2.dtos.CustomerRegDto;
import com.example.couponprojectphase2.dtos.LoginResDto;
import com.example.couponprojectphase2.exceptions.SecurityException;
import com.example.couponprojectphase2.security.ClientType;

public interface WelcomeService {

    void registerCompany(CompanyRegDto dto) throws SecurityException;

    void registerCustomer(CustomerRegDto dto) throws SecurityException;

    LoginResDto login(String email, String password, ClientType clientType) throws SecurityException;

}
