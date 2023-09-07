package pers.zymir.compute.model.dto;

import lombok.Data;
import pers.zymir.compute.model.req.CouponDiscountComputeReq;

import java.util.List;

@Data
public class CouponComputeDTO {
    private CouponDiscountComputeReq shoppingCart;
    private List<CouponInfoDTO> coupons;
}
