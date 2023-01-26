package com.example.couponprojectphase2.beans;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Admin {
    final private int id = 37;
    final private String email = "admin@admin.com";
    final private String password = "admin";
}
