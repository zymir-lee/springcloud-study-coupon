package pers.zymir.coupon.compute.service.impl;

import org.springframework.stereotype.Component;
import pers.zymir.coupon.compute.service.AbstractCouponCalculator;
import pers.zymir.coupon.template.enums.CouponTypeEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DiscountCalculator extends AbstractCouponCalculator {
    @Override
    protected long doCalculate(long total, long quota) {
        // 折扣 quota=95折
        BigDecimal discountRate = BigDecimal.valueOf(quota).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN);
        BigDecimal discountPrice = BigDecimal.valueOf(total).multiply(discountRate);
        return total - discountPrice.longValue();
    }

    @Override
    public Integer applyCouponType() {
        return CouponTypeEnum.DISCOUNT.getCode();
    }
}
