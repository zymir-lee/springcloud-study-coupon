package pers.zymir.compute.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDTO {
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
