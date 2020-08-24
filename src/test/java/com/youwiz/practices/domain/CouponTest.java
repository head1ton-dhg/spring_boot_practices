package com.youwiz.practices.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CouponTest {

    @Test
    public void builder_test() {
        final Coupon coupon = Coupon.builder()
                .discountAmount(10)
                .build();

        assertEquals(coupon.getDiscountAmount(), 10);
        assertFalse(coupon.isUse());
    }
}