package com.youwiz.practices.controller;

import com.youwiz.practices.domain.Coupon;
import com.youwiz.practices.domain.Order;
import com.youwiz.practices.service.CouponService;
import com.youwiz.practices.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CouponService couponService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Order getOrders(@PathVariable("id") long id) {
        return orderService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Order getOrders() {
        return orderService.order();
    }

    @RequestMapping(value = "coupons/{id}", method = RequestMethod.GET)
    public Coupon getCoupon(@PathVariable("id") long id) {
        return couponService.findById(id);
    }
}
