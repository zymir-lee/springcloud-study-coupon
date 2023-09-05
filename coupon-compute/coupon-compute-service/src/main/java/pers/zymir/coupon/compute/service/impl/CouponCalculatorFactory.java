package pers.zymir.coupon.compute.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.zymir.coupon.compute.service.CouponCalculator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CouponCalculatorFactory {

    private static Map<Integer, CouponCalculator> strategyMapping;

    @Autowired
    public void init(List<CouponCalculator> couponComputeServices) {
        strategyMapping = couponComputeServices.stream()
                .collect(Collectors.toMap(CouponCalculator::applyCouponType, Function.identity()));
    }

    public static CouponCalculator fromCouponType(Integer couponType) {
        return strategyMapping.get(couponType);
    }
}
