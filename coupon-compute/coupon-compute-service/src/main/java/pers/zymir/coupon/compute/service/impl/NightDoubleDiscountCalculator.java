package pers.zymir.coupon.compute.service.impl;

import org.assertj.core.util.DateUtil;
import org.springframework.stereotype.Component;
import pers.zymir.coupon.compute.service.AbstractCouponCalculator;
import pers.zymir.coupon.template.enums.CouponTypeEnum;

@Component
public class NightDoubleDiscountCalculator extends AbstractCouponCalculator {
    @Override
    protected long doCalculate(long total, long quota) {
        // 夜间双倍
        int hour = DateUtil.hourOfDayOf(DateUtil.now());
        if (hour > 20) {
            quota *= 2;
        }
        return Math.min(total, quota);
    }

    @Override
    public Integer applyCouponType() {
        return CouponTypeEnum.DOUBLE_DISCOUNT_NIGHT.getCode();
    }
}
