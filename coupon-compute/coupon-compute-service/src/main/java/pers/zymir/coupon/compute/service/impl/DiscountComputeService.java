package pers.zymir.coupon.compute.service.impl;

import org.springframework.stereotype.Service;
import pers.zymir.coupon.compute.context.CouponComputeContext;
import pers.zymir.coupon.template.enums.CouponTypeEnum;
import pers.zymir.coupon.template.model.CouponTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DiscountComputeService implements Computer {
    @Override
    public long doCompute(CouponComputeContext couponComputeContext) {
        CouponTemplate couponTemplate = couponComputeContext.getCouponTemplate();
        Long threshold = couponTemplate.getThreshold();
        if (couponTemplate.isLimitShop()) {
            Long shopPrice = couponComputeContext.getShopPriceMapping().getOrDefault(couponTemplate.getShopId(), 0L);
            if (shopPrice < threshold) {
                return 0;
            }
            return Math.min(discount(shopPrice, couponTemplate.getQuota()), shopPrice);
        }

        if (couponComputeContext.getTotalPrice() < threshold) {
            return 0;
        }

        return Math.min(discount(couponComputeContext.getTotalPrice(), couponTemplate.getQuota()), couponComputeContext.getTotalPrice());
    }

    @Override
    public Integer applyCouponType() {
        return CouponTypeEnum.DISCOUNT.getCode();
    }

    private long discount(Long price, Long discount) {
        BigDecimal divide = new BigDecimal(discount).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN);
        return new BigDecimal(price).multiply(divide).longValue();
    }
}
