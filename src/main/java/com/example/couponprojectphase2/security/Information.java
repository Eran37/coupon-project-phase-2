package com.example.couponprojectphase2.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {

    private int id;
    private ClientType type;
    private LocalDateTime time;
    private String email;

}
