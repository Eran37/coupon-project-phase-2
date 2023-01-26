package com.example.couponprojectphase2.repositories;

import com.example.couponprojectphase2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByNameOrEmail(String name, String email);

    boolean existsByEmailAndPassword(String name, String email);

    Company findByEmailAndPassword(String email, String password);

    Company findTop1ByEmail(String email);

}
