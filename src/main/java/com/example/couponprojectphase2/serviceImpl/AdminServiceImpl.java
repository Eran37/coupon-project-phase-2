package com.example.couponprojectphase2.serviceImpl;

import com.example.couponprojectphase2.beans.Company;
import com.example.couponprojectphase2.beans.Coupon;
import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.dtos.CompanyDto;
import com.example.couponprojectphase2.dtos.CouponDto;
import com.example.couponprojectphase2.dtos.CustomerDto;
import com.example.couponprojectphase2.exceptions.CouponSystemException;
import com.example.couponprojectphase2.exceptions.ErrMsg;
import com.example.couponprojectphase2.mappers.CompanyMapper;
import com.example.couponprojectphase2.mappers.CustomerMapper;
import com.example.couponprojectphase2.services.AdminService;
import com.example.couponprojectphase2.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl extends ClientService implements AdminService {

    private final CompanyMapper companyMapper;
    private final CustomerMapper customerMapper;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        return false;
    }

    @Override
    public Company getCompany(int companyID) {
        return companyRepository.getById(companyID);
    }

    @Override
    public Customer getCustomer(int customerID) {
        return customerRepository.getById(customerID);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void updateCompany(int companyID, CompanyDto dto) throws CouponSystemException {
        if (companyRepository.existsByNameOrEmail(dto.getFirmName(), dto.getContact()))
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        dto.setId(companyID);
        companyRepository.saveAndFlush(companyMapper.toDao(dto));
    }

    @Override
    public void updateCustomer(int customerID, CustomerDto dto) throws CouponSystemException {
        if (!customerRepository.existsByEmail(dto.getContact())) throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        dto.setId(customerID);
        customerRepository.saveAndFlush(customerMapper.toDao(dto));
    }


    @Override
    public void deleteCompany(int companyID) throws CouponSystemException {
        if (!companyRepository.existsById(companyID)) throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        companyRepository.deleteById(companyID);
    }

    @Override
    public void deleteCustomer(int customerID) throws CouponSystemException {
        if (!customerRepository.existsById(customerID)) throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        customerRepository.deleteById(customerID);
    }


    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
}
