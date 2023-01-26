package com.example.couponprojectphase2.serviceImpl;
import com.example.couponprojectphase2.beans.Category;
import com.example.couponprojectphase2.beans.Coupon;
import com.example.couponprojectphase2.dtos.CompanyDto;
import com.example.couponprojectphase2.dtos.CouponDto;
import com.example.couponprojectphase2.exceptions.CouponSystemException;
import com.example.couponprojectphase2.exceptions.ErrMsg;
import com.example.couponprojectphase2.mappers.CompanyMapper;
import com.example.couponprojectphase2.mappers.CouponMapper;
import com.example.couponprojectphase2.services.ClientService;
import com.example.couponprojectphase2.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService {

    private final CouponMapper mapper;
    private final CompanyMapper companyMapper;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        return false;
    }

    public void addCoupon(int companyId, CouponDto dto) throws CouponSystemException {
        if (couponRepository.existsByTitleAndCompanyId(dto.getCaption(), companyId))
            throw new CouponSystemException(ErrMsg.TITLE_ALREADY_EXIST);
        couponRepository.save(mapper.toDao(dto));
    }

    @Override
    public void updateCoupon(int companyId, int couponId, CouponDto dto) throws CouponSystemException {
        Coupon coupon = mapper.toDao(dto); coupon.setId(couponId);
        if (!couponRepository.existsByTitle(dto.getCaption()))
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int companyId, int couponId) throws CouponSystemException {
        if(!couponRepository.existsByIdAndCompanyId(couponId, companyId))
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        companyRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getAllCoupons(int companyId) {
        return couponRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<CouponDto> getAllCouponsByCategory(int companyId, Category category) {
        return mapper.toDtoList(couponRepository.findAllByCompanyIdAndCategory(companyId, category));
    }

    @Override
    public List<CouponDto> getAllCouponsByMaxPrice(int companyId, double price) {
        return mapper.toDtoList(couponRepository.findAllByCompanyIdAndPriceLessThanEqual(companyId, price));
    }

    @Override
    public CompanyDto getCompanyDetails(int companyId) throws CouponSystemException {
        if (!companyRepository.existsById(companyId))
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        return companyMapper.toDto(companyRepository.getById(companyId));
    }


}
