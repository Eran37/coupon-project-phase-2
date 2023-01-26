package com.example.couponprojectphase2.clr;

import com.example.couponprojectphase2.beans.Category;
import com.example.couponprojectphase2.beans.Company;
import com.example.couponprojectphase2.beans.Coupon;
import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.repositories.CompanyRepository;
import com.example.couponprojectphase2.repositories.CouponRepository;
import com.example.couponprojectphase2.repositories.CustomerRepository;
import com.example.couponprojectphase2.serviceImpl.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Component
@Order(1)
@RequiredArgsConstructor
public class ServiceTest implements CommandLineRunner {

    private static final int COMPANIES = 10;
    private static final int CUSTOMERS = 20;
    private static final int COUPONS_PER_COMPANY = 5;
    private static final int TOTAL_COUPONS = COMPANIES + COUPONS_PER_COMPANY;

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final AdminServiceImpl adminService;

    private static final Random random = new Random();


    @Override
    public void run(String... args) {

        for (int i = 0; i < COMPANIES; i++) {
            Company c = Company.builder()
                    .name("company " + i)
                    .email("company@" + i + ".com")
                    .password("pass" + i)
                    .build();
            companyRepository.save(c);
        }


        for (int i = 0; i < CUSTOMERS; i++) {
            Customer c = Customer.builder()
                    .firstName("firstName" + i)
                    .lastName("lastName" + i)
                    .email("customer@" + i + ".com")
                    .password("pass" + i)
                    .build();
            customerRepository.save(c);
        }

        for (int i = 0; i < COMPANIES; i++) {
            for (int j = 0; j < COUPONS_PER_COMPANY; j++) {
                Coupon c = Coupon.builder()
                        .title("title" + j)
                        .company(adminService.getCompany(i))
                        .image("image" + j)
                        .price(j)
                        .description("description" + j)
                        .amount(50)
                        .startDate(Timestamp.valueOf(LocalDateTime.now()))
                        .endDate(Timestamp.valueOf(LocalDateTime.now().plusDays(30)))
                        .category(Category.values()[random.nextInt(Category.values().length)])
                        .build();
                couponRepository.save(c);
            }
        }

        for (int i = 1; i <= CUSTOMERS; i++) {
            Optional<Customer> customer = customerRepository.findById(i);
            if (customer.isPresent()) {
                List<Coupon> coupons = new ArrayList<>();
                for (int j = 0; j < 20; j++) {
                    coupons.add(couponRepository.getById(random.nextInt(TOTAL_COUPONS) + 1));
                }
                customer.get().setCoupons(coupons);
                customerRepository.saveAndFlush(customer.get());
            }
        }


    }
}
