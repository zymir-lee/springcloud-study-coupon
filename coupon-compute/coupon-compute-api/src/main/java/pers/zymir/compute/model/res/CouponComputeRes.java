package pers.zymir.compute.model.res;

import lombok.Data;

import java.util.Map;

@Data
public class CouponComputeRes {
    /**
     * 最优惠的优惠券价格
     */
    private Long bestCouponTemplateId;

    /**
     * 每种优惠券可折扣金额
     */
    private Map<Long, Long> discountMapping;
}
