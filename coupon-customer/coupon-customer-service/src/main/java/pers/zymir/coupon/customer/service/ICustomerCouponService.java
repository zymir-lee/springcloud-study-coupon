package pers.zymir.coupon.customer.service;

import pers.zymir.coupon.customer.model.Coupon;
import pers.zymir.coupon.customer.req.ReceiveCouponReq;

import java.util.List;

public interface ICustomerCouponService {
    boolean receiveCoupon(ReceiveCouponReq receiveCouponReq);

    List<Coupon> queryUserAvailableCoupons(long userId);
}
