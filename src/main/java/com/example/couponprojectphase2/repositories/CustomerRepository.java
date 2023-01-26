package com.example.couponprojectphase2.repositories;

import com.example.couponprojectphase2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Customer findByEmailAndPassword(String email, String password);

    Customer findTop1ByEmail(String email);

}
