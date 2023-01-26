package com.example.couponprojectphase2.repositories;


import com.example.couponprojectphase2.beans.Category;
import com.example.couponprojectphase2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByTitle(String title);

    boolean existsByTitleAndCompanyId(String title, int companyId);

    boolean existsByIdAndCompanyId(int companyId, int couponId);

    List<Coupon> findAllByCompanyId(int companyId);

    List<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category);

    List<Coupon> findAllByCompanyIdAndPriceLessThanEqual(int companyId, double price);

    @Modifying
    void deleteByEndDateBefore(Timestamp expiryDate);


}
