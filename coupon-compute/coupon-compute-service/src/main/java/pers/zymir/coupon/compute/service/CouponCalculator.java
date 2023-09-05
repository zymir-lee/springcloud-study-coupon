package pers.zymir.coupon.compute.service;

import pers.zymir.coupon.compute.context.CouponComputeContext;

public interface CouponCalculator {
    long calculate(CouponComputeContext couponComputeContext);

    Integer applyCouponType();
}
