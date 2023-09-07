package pers.zymir.compute.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductItemDTO {

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
