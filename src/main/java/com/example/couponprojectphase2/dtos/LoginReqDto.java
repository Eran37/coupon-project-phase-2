package com.example.couponprojectphase2.dtos;

import com.example.couponprojectphase2.security.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReqDto {


    @Email // valid email for DNS servers
    private String email;

    @Length(min = 3, max = 8)
    private String password;

    private ClientType clientType;

}
