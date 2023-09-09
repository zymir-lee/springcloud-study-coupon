package pers.zymir.coupon.customer.res;

import lombok.Data;

import java.util.Map;

@Data
public class CustomerCouponDiscountComputeRes {
    private Long bestCouponId;
    private Map<Long, Long> eachCouponDiscountMapping;
}
