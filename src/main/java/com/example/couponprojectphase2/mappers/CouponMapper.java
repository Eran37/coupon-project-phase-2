package com.example.couponprojectphase2.mappers;

import com.example.couponprojectphase2.beans.Category;
import com.example.couponprojectphase2.beans.Coupon;
import com.example.couponprojectphase2.dtos.CouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponMapper implements Mapper<Coupon, CouponDto>{

    @Override
    public Coupon toDao(CouponDto dto) {
        return Coupon.builder()
                .category(Category.valueOf(dto.getClassification()))
                .title(dto.getCaption())
                .description(dto.getDetails())
                .startDate(Timestamp.valueOf(dto.getCreationDate()))
                .endDate(Timestamp.valueOf(dto.getExpirationDate()))
                .amount(dto.getQuantity())
                .price(dto.getCost())
                .image(dto.getPhoto())
                .build();
    }

    @Override
    public CouponDto toDto(Coupon dao) {
        return CouponDto.builder()
                .id(dao.getId())
                .classification(dao.getCategory().toString())
                .caption(dao.getTitle())
                .caption(dao.getTitle())
                .details(dao.getDescription())
                .creationDate(dao.getStartDate().toLocalDateTime())
                .expirationDate(dao.getEndDate().toLocalDateTime())
                .quantity(dao.getAmount())
                .cost(dao.getPrice())
                .photo(dao.getImage())
                .build();
    }

    @Override
    public List<Coupon> toDaoList(List<CouponDto> dtoList) {
        return dtoList.stream().map(this::toDao).collect(Collectors.toList());
    }

    @Override
    public List<CouponDto> toDtoList(List<Coupon> daoList) {
        return daoList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
