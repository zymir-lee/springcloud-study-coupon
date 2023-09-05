package pers.zymir.coupon.compute.dto;

import lombok.Data;
import pers.zymir.coupon.compute.model.ShoppingCart;

import java.util.List;

@Data
public class CouponComputeDTO {
    private ShoppingCart shoppingCart;
    private List<CouponInfoDTO> coupons;

    @Data
    public static class CouponInfoDTO {
        private Long id;
        private Long templateId;
    }
}
