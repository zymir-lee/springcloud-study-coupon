package pers.zymir.coupon.compute.service;

import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.zymir.coupon.compute.context.CouponComputeContext;
import pers.zymir.coupon.compute.model.CartProductItem;
import pers.zymir.coupon.compute.model.ShoppingCart;
import pers.zymir.coupon.compute.res.CouponComputeRes;
import pers.zymir.coupon.compute.service.impl.Computer;
import pers.zymir.coupon.compute.service.impl.CouponComputeFactory;
import pers.zymir.coupon.customer.model.Coupon;
import pers.zymir.coupon.customer.service.ICustomerCouponService;
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
    private ICustomerCouponService customerCouponService;

    @Autowired
    private ICouponTemplateService couponTemplateService;

    @Override
    public CouponComputeRes compute(ShoppingCart shoppingCart) {
        CouponComputeRes couponComputeRes = new CouponComputeRes();
        List<Coupon> coupons = customerCouponService.queryUserAvailableCoupons(shoppingCart.getUserId());
        if (CollUtil.isEmpty(coupons)) {
            return couponComputeRes;
        }
        List<Long> couponTemplateIds = StreamUtil.mapTo(coupons, Coupon::getCouponTemplateId);
        Map<Long, CouponTemplate> couponTemplateIdMapping = couponTemplateService.couponTemplateIdMapping(couponTemplateIds);

        Map<Long, Long> couponDiscountMapping = new HashMap<>();
        Map<Long, Long> cache = new HashMap<>();

        Map<Long, Long> shopPriceMapping = shoppingCart.getProductItems().stream()
                .collect(Collectors.groupingBy(CartProductItem::getShopId, Collectors.summingLong(item -> item.getPrice() * item.getCount())));
        for (Coupon each : coupons) {
            Long couponTemplateId = each.getCouponTemplateId();
            Long cacheRes = cache.get(each.getCouponTemplateId());
            if (Objects.nonNull(cacheRes)) {
                couponDiscountMapping.put(each.getId(), cacheRes);
                continue;
            }
            CouponTemplate couponTemplate = couponTemplateIdMapping.get(couponTemplateId);
            CouponComputeContext context = CouponComputeContext.builder()
                    .couponTemplate(couponTemplate)
                    .coupon(each)
                    .totalPrice(computeTotalPrice(shoppingCart.getProductItems()))
                    .shopPriceMapping(shopPriceMapping)
                    .build();
            Computer computer = CouponComputeFactory.fromCouponType(couponTemplate.getCouponType());
            long discountPrice = computer.doCompute(context);
            couponDiscountMapping.put(each.getId(), discountPrice);
            cache.put(each.getCouponTemplateId(), discountPrice);
        }

        long bestDiscountCouponId = findBestDiscountCouponId(couponDiscountMapping);
        couponComputeRes.setBestCouponId(bestDiscountCouponId);
        couponComputeRes.setDiscountMapping(couponDiscountMapping);
        return couponComputeRes;
    }

    private long findBestDiscountCouponId(Map<Long, Long> couponDiscountMapping) {
        long bestId = 0;
        long discount = 0;
        for (Map.Entry<Long, Long> each : couponDiscountMapping.entrySet()) {
            if (each.getValue() > discount) {
                bestId = each.getKey();
                discount = each.getValue();
            }
        }
        return bestId;
    }

    private long computeTotalPrice(List<CartProductItem> cartProductItems) {
        long total = 0;
        for (CartProductItem each : cartProductItems) {
            total += computePrice(each);
        }
        return total;
    }

    private long computePrice(CartProductItem cartProductItems) {
        return cartProductItems.getPrice() * cartProductItems.getCount();
    }
}