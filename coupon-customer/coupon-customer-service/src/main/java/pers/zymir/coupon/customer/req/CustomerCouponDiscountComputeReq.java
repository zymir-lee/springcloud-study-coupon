package pers.zymir.coupon.customer.req;

import lombok.Data;
import pers.zymir.coupon.customer.dto.ProductItemDTO;

import java.util.List;

@Data
public class CustomerCouponDiscountComputeReq {
    private Long userId;
    private List<ProductItemDTO> productItems;
}
