package com.youwiz.practices.service;

import com.youwiz.practices.domain.Coupon;
import com.youwiz.practices.domain.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CouponService couponService;

    @Test
    public void order_쿠폰할인적용() {
        final Order order = orderService.order();

        assertEquals(order.getPrice(), 9_000D); // 1,000 할인 적용
        assertNotNull(order.getId());   // 1,000할인 적용
        assertNotNull(order.getCoupon());

        final Order findOrder = orderService.findById(order.getId());
        System.out.println("couponId : " + findOrder.getCoupon().getId());  // couponId : 1 (coupon_id 외래 키를 저장 완료)

        final Coupon coupon = couponService.findById(1);
        assertTrue(coupon.isUse());
        assertNotNull(coupon.getId());
        assertNotNull(coupon.getDiscountAmount());
    }

    @Test
    public void use_메서드에_order_set_필요이유() {
        final Order order = orderService.order();
        assertEquals(order.getPrice(), 9_000D);
        final Coupon coupon = order.getCoupon();
        assertNotNull(coupon.getOrder());
    }
}