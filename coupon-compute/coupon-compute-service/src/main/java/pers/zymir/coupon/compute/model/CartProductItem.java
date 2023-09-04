package pers.zymir.coupon.compute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductItem {

    /**
     * 单价
     */
    private Long price;

    /**
     * 数量
     */
    private Long count;

    /**
     * 店铺id
     */
    private Long shopId;
}
