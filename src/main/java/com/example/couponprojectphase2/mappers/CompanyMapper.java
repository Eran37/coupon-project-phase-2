package com.example.couponprojectphase2.mappers;

import com.example.couponprojectphase2.beans.Company;
import com.example.couponprojectphase2.dtos.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyMapper implements Mapper<Company, CompanyDto> {

    @Override
    public Company toDao(CompanyDto dto) {
        return Company.builder()
                .name(dto.getFirmName())
                .email(dto.getContact())
                .password(dto.getSafeWord())
                .build();
    }

    @Override
    public CompanyDto toDto(Company dao) {
        return CompanyDto.builder()
                .id(dao.getId())
                .firmName(dao.getName())
                .contact(dao.getEmail())
                .safeWord(dao.getPassword())
                .build();
    }

    @Override
    public List<Company> toDaoList(List<CompanyDto> dtoList) {
        return dtoList.stream().map(this::toDao).collect(Collectors.toList());
    }

    @Override
    public List<CompanyDto> toDtoList(List<Company> daoList) {
        return daoList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
