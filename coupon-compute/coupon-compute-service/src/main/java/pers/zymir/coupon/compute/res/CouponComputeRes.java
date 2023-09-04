package pers.zymir.coupon.compute.res;

import lombok.Data;

import java.util.Map;

@Data
public class CouponComputeRes {
    /**
     * 最优惠的优惠券价格
     */
    private Long bestCouponId;

    private Map<Long, Long> discountMapping;
}
