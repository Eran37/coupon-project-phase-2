package com.example.couponprojectphase2.mappers;

import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.dtos.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    @Override
    public Customer toDao(CustomerDto dto) {
        return Customer.builder()
                .firstName(dto.getPrivateName())
                .lastName(dto.getFamilyName())
                .email(dto.getContact())
                .password(dto.getSafeWord())
                .build();
    }

    @Override
    public CustomerDto toDto(Customer dao) {
        return CustomerDto.builder()
                .id(dao.getId())
                .privateName(dao.getFirstName())
                .familyName(dao.getLastName())
                .contact(dao.getEmail())
                .safeWord(dao.getPassword())
                .build();
    }

    @Override
    public List<Customer> toDaoList(List<CustomerDto> dtoList) {
        return dtoList.stream().map(this::toDao).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> toDtoList(List<Customer> daoList) {
        return daoList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
