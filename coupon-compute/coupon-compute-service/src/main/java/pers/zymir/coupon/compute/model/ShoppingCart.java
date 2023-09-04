package pers.zymir.coupon.compute.model;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCart {
    /**
     * 购物车内内商品
     */
    private List<CartProductItem> productItems;

    /**
     * 优惠后价格
     */
    private Long afterDiscountPrice;

    /**
     *
     */
    private Long userId;
}
