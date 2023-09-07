package pers.zymir.coupon.compute.service;

import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.zymir.compute.model.dto.CartProductItemDTO;
import pers.zymir.compute.model.dto.CouponComputeDTO;
import pers.zymir.compute.model.dto.CouponInfoDTO;
import pers.zymir.compute.model.dto.ShoppingCartDTO;
import pers.zymir.coupon.compute.context.CouponComputeContext;
import pers.zymir.coupon.compute.res.CouponComputeRes;
import pers.zymir.coupon.compute.service.impl.CouponCalculatorFactory;
import pers.zymir.coupon.template.model.CouponTemplate;
import pers.zymir.coupon.template.service.ICouponTemplateService;
import pers.zymir.util.stream.StreamUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CouponComputeProcess implements ICouponComputeService {

    @Autowired
    private ICouponTemplateService couponTemplateService;

    @Override
    public CouponComputeRes compute(CouponComputeDTO couponComputeDTO) {
        CouponComputeRes couponComputeRes = new CouponComputeRes();

        ShoppingCartDTO shoppingCart = couponComputeDTO.getShoppingCart();
        List<CouponInfoDTO> coupons = couponComputeDTO.getCoupons();
        if (CollUtil.isEmpty(coupons)) {
            return couponComputeRes;
        }
        List<Long> couponTemplateIds = StreamUtil.mapTo(coupons, CouponInfoDTO::getTemplateId);
        Map<Long, CouponTemplate> couponTemplateIdMapping = couponTemplateService.couponTemplateIdMapping(couponTemplateIds);
        Map<Long, Long> couponDiscountMapping = new HashMap<>();
        Map<Long, Long> couponTemplateDiscountCache = new HashMap<>();
        Map<Long, Long> shopPriceMapping = computeEachShopPrice(shoppingCart);
        long bestCouponId = 0;
        long maxDiscount = 0;
        for (CouponInfoDTO each : coupons) {
            Long couponTemplateId = each.getTemplateId();
            Long cacheRes = couponTemplateDiscountCache.get(each.getTemplateId());
            if (Objects.nonNull(cacheRes)) {
                couponDiscountMapping.put(each.getId(), cacheRes);
                continue;
            }
            CouponTemplate couponTemplate = couponTemplateIdMapping.get(couponTemplateId);
            CouponComputeContext context = CouponComputeContext.builder()
                    .couponTemplate(couponTemplate)
                    .totalPrice(computeTotalPrice(shoppingCart.getProductItems()))
                    .shopPriceMapping(shopPriceMapping)
                    .build();
            CouponCalculator couponCalculator = CouponCalculatorFactory.fromCouponType(couponTemplate.getCouponType());
            long discountPrice = couponCalculator.calculate(context);
            if (discountPrice > maxDiscount) {
                maxDiscount = discountPrice;
                bestCouponId = each.getId();
            }
            couponDiscountMapping.put(each.getId(), discountPrice);
            couponTemplateDiscountCache.put(each.getTemplateId(), discountPrice);
        }

        couponComputeRes.setBestCouponId(bestCouponId);
        couponComputeRes.setDiscountMapping(couponDiscountMapping);
        return couponComputeRes;
    }

    private Map<Long, Long> computeEachShopPrice(ShoppingCartDTO shoppingCart) {
        return shoppingCart.getProductItems().stream()
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
