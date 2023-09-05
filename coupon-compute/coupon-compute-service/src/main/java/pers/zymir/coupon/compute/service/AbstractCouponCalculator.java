package pers.zymir.coupon.compute.service;

import pers.zymir.coupon.compute.context.CouponComputeContext;
import pers.zymir.coupon.template.model.CouponTemplate;

import java.util.Map;

public abstract class AbstractCouponCalculator implements CouponCalculator {

    @Override
    public long calculate(CouponComputeContext couponComputeContext) {
        CouponTemplate couponTemplate = couponComputeContext.getCouponTemplate();
        if (couponTemplate.isLimitShop()) {
            Long shopId = couponTemplate.getShopId();
            Map<Long, Long> shopPriceMapping = couponComputeContext.getShopPriceMapping();
            Long shopPrice = shopPriceMapping.getOrDefault(shopId, 0L);
            if (!isReachThreshold(couponTemplate.getThreshold(), shopPrice)) {
                return 0;
            }
            return doCalculate(shopPrice, couponTemplate.getQuota());
        }
        if (!isReachThreshold(couponTemplate.getThreshold(), couponComputeContext.getTotalPrice())) {
            return 0;
        }
        return doCalculate(couponComputeContext.getTotalPrice(), couponTemplate.getQuota());
    }

    protected abstract long doCalculate(long total, long quota);

    private boolean isReachThreshold(long threshold, long paidPrice) {
        return paidPrice >= threshold;
    }
}
