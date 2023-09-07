package pers.zymir.coupon.compute.service;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.zymir.compute.model.dto.CartProductItemDTO;
import pers.zymir.compute.model.dto.CouponTemplateDTO;
import pers.zymir.compute.model.req.CouponDiscountComputeReq;
import pers.zymir.coupon.compute.context.CouponComputeContext;
import pers.zymir.coupon.compute.res.CouponComputeRes;
import pers.zymir.coupon.compute.service.impl.CouponCalculatorFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CouponComputeProcess implements ICouponComputeService {

    @Override
    public CouponComputeRes compute(CouponDiscountComputeReq couponDiscountComputeReq) {
        CouponComputeRes couponComputeRes = new CouponComputeRes();

        List<CouponTemplateDTO> coupons = couponDiscountComputeReq.getCouponTemplates();
        if (CollUtil.isEmpty(coupons)) {
            log.info("优惠价格计算结束 当前用户没有任何可用优惠券 userId：{}", couponDiscountComputeReq.getUserId());
            return couponComputeRes;
        }

        List<CartProductItemDTO> productItems = couponDiscountComputeReq.getProductItems();
        Map<Long, Long> couponTemplateDiscountCache = new HashMap<>();
        Map<Long, Long> shopPriceMapping = computeEachShopPrice(productItems);
        long bestCouponTemplateId = -1;
        long maxDiscount = 0;
        for (CouponTemplateDTO each : coupons) {
            if (couponTemplateDiscountCache.containsKey(each.getCouponTemplateId())) {
                continue;
            }

            CouponComputeContext context = CouponComputeContext.builder()
                    .couponTemplateDTO(each)
                    .totalPrice(computeTotalPrice(productItems))
                    .shopPriceMapping(shopPriceMapping)
                    .build();
            CouponCalculator couponCalculator = CouponCalculatorFactory.fromCouponType(each.getCouponType());
            long discountPrice = couponCalculator.calculate(context);
            if (discountPrice > maxDiscount) {
                maxDiscount = discountPrice;
                bestCouponTemplateId = each.getCouponTemplateId();
            }
            couponTemplateDiscountCache.put(each.getCouponTemplateId(), discountPrice);
        }

        couponComputeRes.setBestCouponTemplateId(bestCouponTemplateId);
        couponComputeRes.setDiscountMapping(couponTemplateDiscountCache);
        return couponComputeRes;
    }

    private Map<Long, Long> computeEachShopPrice(List<CartProductItemDTO> productItems) {
        return productItems.stream()
                .collect(Collectors.groupingBy(CartProductItemDTO::getShopId, Collectors.summingLong(item -> item.getPrice() * item.getCount())));
    }

    private long computeTotalPrice(List<CartProductItemDTO> cartProductItems) {
        long total = 0;
        for (CartProductItemDTO each : cartProductItems) {
            total += computePrice(each);
        }
        return total;
    }

    private long computePrice(CartProductItemDTO cartProductItems) {
        return cartProductItems.getPrice() * cartProductItems.getCount();
    }
}
