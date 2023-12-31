package pers.zymir.coupon.compute.service;

import pers.zymir.compute.model.req.CouponDiscountComputeReq;
import pers.zymir.compute.model.res.CouponComputeRes;

public interface ICouponComputeService {
    CouponComputeRes compute(CouponDiscountComputeReq couponDiscountComputeReq);
}
