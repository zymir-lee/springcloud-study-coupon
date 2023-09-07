package pers.zymir.compute.model.req;

import lombok.Data;
import pers.zymir.compute.model.dto.CartProductItemDTO;

import java.util.List;

@Data
public class CouponDiscountComputeReq {
    /**
     * 购物车内内商品
     */
    private List<CartProductItemDTO> productItems;

    /**
     * 优惠后价格
     */
    private Long afterDiscountPrice;

    /**
     *
     */
    private Long userId;
}
