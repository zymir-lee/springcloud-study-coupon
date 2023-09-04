package pers.zymir.coupon.compute.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CouponComputeFactory {

    private static Map<Integer, Computer> strategyMapping;

    @Autowired
    List<Computer> couponComputeServices;

    @PostConstruct
    public void init() {
        strategyMapping = couponComputeServices.stream()
                .collect(Collectors.toMap(Computer::applyCouponType, Function.identity()));
    }

    public static Computer fromCouponType(Integer couponType) {
        return strategyMapping.get(couponType);
    }
}
