//package com.example.couponprojectphase2.security;
//
//import com.example.couponprojectphase2.beans.Company;
//import com.example.couponprojectphase2.exceptions.CouponSystemException;
//import com.example.couponprojectphase2.exceptions.ErrMsg;
//import com.example.couponprojectphase2.serviceImpl.CompanyServiceImpl;
//import com.example.couponprojectphase2.serviceImpl.CustomerServiceImpl;
//import com.example.couponprojectphase2.services.AdminService;
//import com.example.couponprojectphase2.services.ClientService;
//import com.example.couponprojectphase2.services.CompanyService;
//import com.example.couponprojectphase2.services.CustomerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@Lazy
//@RequiredArgsConstructor
//public class LoginManager {
//
//
//    // TODO -->
//
//    private final AdminService adminService;
//    private final ApplicationContext ctx;
//
//    public ClientService login(ClientType type, String email, String password) throws CouponSystemException {
//        switch (type) {
//            case COMPANY:
//                Company company = (Company) ctx.getBean(CompanyService.class);
//                if (!ctx.getBean(CompanyService.class).login(email, password))
//                    throw new CouponSystemException(ErrMsg.EMAIL_OR_PASSWORD_INCORRECT);
//                return ctx.getBean(CompanyServiceImpl.class);
//            case CUSTOMER:
//                if (!ctx.getBean(CustomerService.class).login(email, password))
//                    throw new CouponSystemException(ErrMsg.EMAIL_OR_PASSWORD_INCORRECT);
//                return ctx.getBean(CustomerServiceImpl.class);
//            case ADMINISTRATOR:
//                if (adminService.login(email, password))
//                    return (ClientService) adminService;
//                throw new CouponSystemException(ErrMsg.EMAIL_OR_PASSWORD_INCORRECT);
//        }
//        return null;
//    }
//
//
//}
