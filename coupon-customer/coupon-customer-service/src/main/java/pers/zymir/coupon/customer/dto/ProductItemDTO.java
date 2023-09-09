package pers.zymir.coupon.customer.dto;

import lombok.Data;

@Data
public class ProductItemDTO {
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
