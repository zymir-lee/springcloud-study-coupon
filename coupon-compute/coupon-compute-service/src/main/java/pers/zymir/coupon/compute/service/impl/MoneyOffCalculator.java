package pers.zymir.coupon.compute.service.impl;

import org.springframework.stereotype.Component;
import pers.zymir.coupon.compute.service.AbstractCouponCalculator;
import pers.zymir.coupon.template.enums.CouponTypeEnum;

@Component
public class MoneyOffCalculator extends AbstractCouponCalculator {
    @Override
    protected long doCalculate(long total, long quota) {
        // 满减
        return Math.min(total, quota);
    }

    @Override
    public Integer applyCouponType() {
        return CouponTypeEnum.MONEY_OFF.getCode();
    }
}
