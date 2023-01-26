package com.example.couponprojectphase2.processes;

import com.example.couponprojectphase2.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ExpiredCouponsRemoval {

    private final CouponRepository repository;

    @Scheduled(fixedRate = 1000 * 60)
    public void expiredCouponsRemoval() {
//        repository.deleteByEndDateBefore(Date.valueOf(LocalDate.now()));
        System.out.println("EXPIRED-COUPONS-REMOVED");
    }

}
