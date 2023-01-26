package com.example.couponprojectphase2.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private int id;

    @NotBlank
    private String firmName;

    @Email
    @NotBlank
    private String contact;

    @NotBlank
    @Length(min = 4, max = 8)
    private String safeWord;

    @Singular
    private List<CouponDto> products = new ArrayList<>();


}
