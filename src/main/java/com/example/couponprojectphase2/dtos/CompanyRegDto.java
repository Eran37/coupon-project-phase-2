package com.example.couponprojectphase2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRegDto {

    @NotBlank
    private String firmName;
    @Email
    @NotBlank
    private String contact;
    @Length(min = 4, max = 8)
    @NotBlank
    private String safeWord;
    @Length(min = 4, max = 8)
    @NotBlank
    private String confirm;


}
