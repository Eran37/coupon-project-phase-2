package com.example.couponprojectphase2.mappers;

import java.util.List;

public interface Mapper <DAO, DTO> {

    DAO toDao(DTO dto);
    DTO toDto(DAO dao);

    List<DAO> toDaoList(List<DTO> dtoList);
    List<DTO> toDtoList(List<DAO> daoList);

}
