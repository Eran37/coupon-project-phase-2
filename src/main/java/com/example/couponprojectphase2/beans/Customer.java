package com.example.couponprojectphase2.beans;

import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Singular
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Coupon> coupons = new ArrayList<>();


}
