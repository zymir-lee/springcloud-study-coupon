package pers.zymir.coupon.compute.service.impl;

import org.springframework.stereotype.Service;
import pers.zymir.coupon.compute.context.CouponComputeContext;
import pers.zymir.coupon.template.enums.CouponTypeEnum;
import pers.zymir.coupon.template.model.CouponTemplate;

@Service
public class MoneyOffCouponComputeService implements Computer {
    @Override
    public long doCompute(CouponComputeContext couponComputeContext) {
        CouponTemplate couponTemplate = couponComputeContext.getCouponTemplate();
        Long threshold = couponTemplate.getThreshold();
        if (couponTemplate.isLimitShop()) {
            Long shopPrice = couponComputeContext.getShopPriceMapping().getOrDefault(couponTemplate.getShopId(), 0L);
            if (shopPrice < threshold) {
                return 0;
            }
            return Math.min(shopPrice, couponTemplate.getQuota());
        }

        if (couponComputeContext.getTotalPrice() < threshold) {
            return 0;
        }

        return couponTemplate.getQuota();
    }

    @Override
    public Integer applyCouponType() {
        return CouponTypeEnum.MONEY_OFF.getCode();
    }
}
