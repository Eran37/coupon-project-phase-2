package com.example.couponprojectphase2.controllers;

import com.example.couponprojectphase2.dtos.*;
import com.example.couponprojectphase2.exceptions.SecurityException;
import com.example.couponprojectphase2.repositories.CouponRepository;
import com.example.couponprojectphase2.security.TokenManager;
import com.example.couponprojectphase2.services.WelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/welcome")
@CrossOrigin(origins = "*")
public class WelcomeController {

    private final WelcomeService service;
    private final TokenManager tokenManager;
    private final CouponRepository couponRepository;

    @PostMapping("register/company")
    @ResponseStatus(HttpStatus.CREATED) // err 201 - created
    public void registerCompany(@Valid @RequestBody CompanyRegDto dto) throws SecurityException {
        service.registerCompany(dto);
    }

    @PostMapping("register/customer")
    @ResponseStatus(HttpStatus.CREATED) // err 201 - created
    public void registerCustomer(@Valid @RequestBody CustomerRegDto dto) throws SecurityException {
        service.registerCustomer(dto);
    }

    @PostMapping("login") // POST because it creates a UUID token
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@Valid @RequestBody LoginReqDto reqDto) throws SecurityException {
        return service.login(reqDto.getEmail(), reqDto.getPassword(), reqDto.getClientType());
    }

}
