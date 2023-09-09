package pers.zymir.coupon.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pers.zymir.compute.model.req.CouponDiscountComputeReq;
import pers.zymir.compute.model.res.CouponComputeRes;
import pers.zymir.coupon.customer.client.fallback.CouponComputeClientFallbackFactory;

@FeignClient(
        name = "coupon-compute",
        path = "/coupon-compute",
        fallbackFactory = CouponComputeClientFallbackFactory.class
)
public interface CouponComputeClient {

    @PostMapping
    CouponComputeRes compute(@RequestBody CouponDiscountComputeReq couponDiscountComputeReq);
}
