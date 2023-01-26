package com.example.couponprojectphase2.services;

import com.example.couponprojectphase2.beans.Company;
import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.dtos.CompanyDto;
import com.example.couponprojectphase2.dtos.CustomerDto;
import com.example.couponprojectphase2.exceptions.CouponSystemException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AdminService {

    boolean login(String email, String password) throws CouponSystemException;

    Company getCompany(int companyID) throws CouponSystemException;
    Customer getCustomer(int customerID) throws CouponSystemException;

    void updateCompany(int companyID, CompanyDto dto) throws CouponSystemException;
    void updateCustomer(int customerID, CustomerDto dto) throws CouponSystemException;

    void deleteCompany(int companyID) throws CouponSystemException;
    void deleteCustomer(int customerID) throws CouponSystemException;

    List<Company> getAllCompanies();
    List<Customer> getAllCustomers();

}
