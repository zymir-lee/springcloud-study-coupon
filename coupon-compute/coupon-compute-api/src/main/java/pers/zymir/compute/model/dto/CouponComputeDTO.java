package pers.zymir.compute.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CouponComputeDTO {
    private ShoppingCartDTO shoppingCart;
    private List<CouponInfoDTO> coupons;
}
