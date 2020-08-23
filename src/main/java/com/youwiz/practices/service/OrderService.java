package com.youwiz.practices.service;

import com.youwiz.practices.domain.Coupon;
import com.youwiz.practices.domain.Order;
import com.youwiz.practices.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CouponService couponService;

    public Order order() {
        final Order order = Order.builder().price(1_0000).build();
        Coupon coupon = couponService.findById(1);  // 1,000 할인 쿠폰
        order.applyCoupon(coupon);
        return orderRepository.save(order);
    }

    public Order findById(long id) {
        return orderRepository.findById(id).get();
    }
}
