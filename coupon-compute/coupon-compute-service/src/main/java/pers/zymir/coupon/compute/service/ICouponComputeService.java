package pers.zymir.coupon.compute.service;

import pers.zymir.coupon.compute.model.ShoppingCart;
import pers.zymir.coupon.compute.res.CouponComputeRes;

public interface ICouponComputeService {
    CouponComputeRes compute(ShoppingCart shoppingCart);
}
