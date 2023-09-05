package pers.zymir.coupon.compute.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.zymir.coupon.template.model.CouponTemplate;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponComputeContext {
    private CouponTemplate couponTemplate;
    private Long totalPrice;
    private Map<Long, Long> shopPriceMapping;
}

