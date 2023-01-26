package com.example.couponprojectphase2.controllers;

import com.example.couponprojectphase2.beans.Admin;
import com.example.couponprojectphase2.beans.Company;
import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.dtos.CompanyDto;
import com.example.couponprojectphase2.dtos.CouponDto;
import com.example.couponprojectphase2.dtos.CustomerDto;
import com.example.couponprojectphase2.exceptions.CouponSystemException;
import com.example.couponprojectphase2.exceptions.ErrMsg;
import com.example.couponprojectphase2.exceptions.SecurityException;
import com.example.couponprojectphase2.mappers.CompanyMapper;
import com.example.couponprojectphase2.mappers.CouponMapper;
import com.example.couponprojectphase2.mappers.CustomerMapper;
import com.example.couponprojectphase2.security.ClientType;
import com.example.couponprojectphase2.security.TokenManager;
import com.example.couponprojectphase2.serviceImpl.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/admin")
//@CrossOrigin(origins = "http://localhost:3000/api/admin")
public class AdminController {

    private final AdminServiceImpl service;
    private final TokenManager tokenManager;

    private final CompanyMapper companyMapper;
    private final CustomerMapper customerMapper;
    private final CouponMapper couponMapper;

    @GetMapping("{companyId}")
    public Company getCompanyById(@PathVariable int companyId, @RequestHeader("Authorization")UUID token) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.ADMINISTRATOR))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return service.getCompany(companyId);
    }

    @GetMapping("{customerId}")
    public Customer getCustomerById(@PathVariable int customerId, @RequestHeader("Authorization")UUID token) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.ADMINISTRATOR))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return service.getCustomer(customerId);
    }

    @GetMapping("companies")
    public List<CompanyDto> getAllCompanies(@RequestHeader("Authorization") UUID token) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.ADMINISTRATOR))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return companyMapper.toDtoList(service.getAllCompanies());
    }
    @GetMapping("customers")
    public List<CustomerDto> getAllCustomers(@RequestHeader("Authorization")UUID token) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.ADMINISTRATOR))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return customerMapper.toDtoList(service.getAllCustomers());
    }

    @GetMapping("coupons")
    public List<CouponDto> getAllCoupons(@RequestHeader("Authorization")UUID token) throws SecurityException, CouponSystemException {
        if (!tokenManager.getClientType(token).equals(ClientType.ADMINISTRATOR))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        return couponMapper.toDtoList(service.getAllCoupons());
    }

    @PutMapping("{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int companyId, @RequestHeader("Authorization")UUID token, @RequestBody CompanyDto dto) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.ADMINISTRATOR))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        service.updateCompany(companyId, dto);
    }

    @PutMapping("{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId, @RequestHeader("Authorization")UUID token, @RequestBody CustomerDto dto) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.ADMINISTRATOR))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        service.updateCustomer(customerId, dto);
    }

    @DeleteMapping("{companyId}")
    public void deleteCompany(@PathVariable int companyId, @RequestHeader("Authorization")UUID token) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.ADMINISTRATOR))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        service.deleteCompany(companyId);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable int customerId, @RequestHeader("Authorization")UUID token) throws CouponSystemException, SecurityException {
        if (!tokenManager.getClientType(token).equals(ClientType.ADMINISTRATOR))
            throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        service.deleteCustomer(customerId);
    }


}
