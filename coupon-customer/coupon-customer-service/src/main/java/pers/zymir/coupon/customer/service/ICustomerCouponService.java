package pers.zymir.coupon.customer.service;

import pers.zymir.coupon.customer.model.Coupon;
import pers.zymir.coupon.customer.req.CustomerCouponDiscountComputeReq;
import pers.zymir.coupon.customer.req.ReceiveCouponReq;
import pers.zymir.coupon.customer.res.CustomerCouponDiscountComputeRes;

import java.util.List;

public interface ICustomerCouponService {
    boolean receiveCoupon(ReceiveCouponReq receiveCouponReq);

    List<Coupon> queryUserAvailableCoupons(long userId);

    CustomerCouponDiscountComputeRes computeCouponDiscount(CustomerCouponDiscountComputeReq request);
}
