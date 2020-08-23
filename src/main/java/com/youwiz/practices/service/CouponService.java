package com.youwiz.practices.service;

import com.youwiz.practices.domain.Coupon;
import com.youwiz.practices.repository.CouponRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public Coupon findById(long id) {
        return couponRepository.findById(id).get();
    }
}
