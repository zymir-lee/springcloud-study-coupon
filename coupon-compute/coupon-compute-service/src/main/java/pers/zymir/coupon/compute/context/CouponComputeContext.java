package pers.zymir.coupon.compute.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.zymir.compute.model.dto.CouponTemplateDTO;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponComputeContext {
    private CouponTemplateDTO couponTemplateDTO;
    private Long totalPrice;
    private Map<Long, Long> shopPriceMapping;
}

