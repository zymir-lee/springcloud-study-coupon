package pers.zymir.coupon.compute.service;

import pers.zymir.compute.model.dto.CouponComputeDTO;
import pers.zymir.coupon.compute.res.CouponComputeRes;

public interface ICouponComputeService {
    CouponComputeRes compute(CouponComputeDTO couponComputeDTO);
}
