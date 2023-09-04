package pers.zymir.coupon.customer.req;

import lombok.Data;

@Data
public class ReceiveCouponReq {
    private Long userId;

    private Long couponTemplateId;
}
