package com.example.couponprojectphase2.serviceImpl;

import com.example.couponprojectphase2.beans.Company;
import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.dtos.CompanyRegDto;
import com.example.couponprojectphase2.dtos.CustomerRegDto;
import com.example.couponprojectphase2.dtos.LoginResDto;
import com.example.couponprojectphase2.exceptions.SecurityException;
import com.example.couponprojectphase2.exceptions.SecurityMsg;
import com.example.couponprojectphase2.mappers.CouponMapper;
import com.example.couponprojectphase2.repositories.CompanyRepository;
import com.example.couponprojectphase2.repositories.CustomerRepository;
import com.example.couponprojectphase2.security.ClientType;
import com.example.couponprojectphase2.security.TokenManager;
import com.example.couponprojectphase2.services.WelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WelcomeServiceImpl implements WelcomeService {

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;

    private final CustomerServiceImpl customerService;
    private final CompanyServiceImpl companyService;
    private final AdminServiceImpl adminService;

    private final CouponMapper couponMapper;
    private final TokenManager tokenManager;

    @Override
    public void registerCompany(CompanyRegDto dto) throws SecurityException {
        if (companyRepository.existsByNameOrEmail(dto.getFirmName(), dto.getContact()))
            throw new SecurityException(SecurityMsg.EMAIL_OR_NAME_ALREADY_EXIST);
        companyRepository.save(Company.builder().name(dto.getFirmName()).email(dto.getContact()).password(dto.getSafeWord()).build());
    }

    @Override
    public void registerCustomer(CustomerRegDto dto) throws SecurityException {
        if (customerRepository.existsByEmail(dto.getContact()))
            throw new SecurityException(SecurityMsg.EMAIL_ALREADY_EXIST);
        customerRepository.save(Customer.builder().firstName(dto.getPrivateName()).lastName(dto.getFamilyName()).email(dto.getContact()).password(dto.getSafeWord()).build());
    }

    @Override
    public LoginResDto login(String email, String password, ClientType clientType) throws SecurityException {
        UUID token = tokenManager.add(email, clientType);
        int id = tokenManager.getUserId(token);
        switch (clientType) {
            case COMPANY:
                if (!companyRepository.existsByEmailAndPassword(email, password))
                    throw new SecurityException(SecurityMsg.EMAIL_OR_PASSWORD_INCORRECT);
                tokenManager.add(email, clientType);
                return new LoginResDto(id, email, token, clientType, couponMapper.toDtoList(companyService.getAllCoupons(id)));
            case CUSTOMER:
                if (!customerRepository.existsByEmailAndPassword(email, password))
                    throw new SecurityException(SecurityMsg.EMAIL_OR_PASSWORD_INCORRECT);
                return new LoginResDto(id, email, token, clientType, couponMapper.toDtoList(customerService.getAllCoupons(id)));
            case ADMINISTRATOR:
                if (!(email.equals("admin@admin.com") && password.equals("admin")))
                    throw new SecurityException(SecurityMsg.EMAIL_OR_PASSWORD_INCORRECT);
                return new LoginResDto(id, email, token, clientType, couponMapper.toDtoList(adminService.getAllCoupons()));
        }
        return null;
    }


}
