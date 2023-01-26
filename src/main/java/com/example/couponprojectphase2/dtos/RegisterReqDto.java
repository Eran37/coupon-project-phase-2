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
public class RegisterReqDto {

    @Email
    private String email;

    @Length(min = 4, max = 8)
    private String password;

    private ClientType clientType;

}
