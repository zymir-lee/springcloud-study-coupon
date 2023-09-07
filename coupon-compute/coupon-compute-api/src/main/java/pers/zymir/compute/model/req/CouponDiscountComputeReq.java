package pers.zymir.compute.model.req;

import lombok.Data;
import pers.zymir.compute.model.dto.CartProductItemDTO;
import pers.zymir.compute.model.dto.CouponTemplateDTO;

import java.util.List;

@Data
public class CouponDiscountComputeReq {
    /**
     * 购物车内内商品
     */
    private List<CartProductItemDTO> productItems;

    private List<CouponTemplateDTO> couponTemplates;

    private Long userId;
}
