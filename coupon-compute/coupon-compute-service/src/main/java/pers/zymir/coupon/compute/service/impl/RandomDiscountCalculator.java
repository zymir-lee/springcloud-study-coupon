package pers.zymir.coupon.compute.service.impl;

import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Component;
import pers.zymir.coupon.compute.service.AbstractCouponCalculator;
import pers.zymir.coupon.template.enums.CouponTypeEnum;

@Component
public class RandomDiscountCalculator extends AbstractCouponCalculator {
    @Override
    protected long doCalculate(long total, long quota) {
        // 随机立减 quota表示最大立减金额
        long random = RandomUtil.randomLong(1L, quota);
        return Math.min(total, random);
    }

    @Override
    public Integer applyCouponType() {
        return CouponTypeEnum.RANDOM_DISCOUNT.getCode();
    }
}
