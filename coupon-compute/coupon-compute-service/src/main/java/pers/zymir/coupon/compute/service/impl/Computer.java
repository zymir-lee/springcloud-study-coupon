package pers.zymir.coupon.compute.service.impl;

import pers.zymir.coupon.compute.context.CouponComputeContext;

public interface Computer {
    long doCompute(CouponComputeContext couponComputeContext);

    Integer applyCouponType();
}
