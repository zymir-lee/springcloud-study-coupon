package pers.zymir.coupon.compute.service;

import pers.zymir.coupon.compute.dto.CouponComputeDTO;
import pers.zymir.coupon.compute.res.CouponComputeRes;

public interface ICouponComputeService {
    CouponComputeRes compute(CouponComputeDTO couponComputeDTO);
}
