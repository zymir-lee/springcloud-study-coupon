package pers.zymir.coupon.compute.service.impl;

import org.springframework.stereotype.Component;
import pers.zymir.coupon.compute.service.AbstractCouponCalculator;
import pers.zymir.coupon.template.enums.CouponTypeEnum;

@Component
public class DefaultCalculator extends AbstractCouponCalculator {
    @Override
    public Integer applyCouponType() {
        return CouponTypeEnum.UNKNOWN.getCode();
    }

    @Override
    protected long doCalculate(long total, long quota) {
        return 0;
    }
}
