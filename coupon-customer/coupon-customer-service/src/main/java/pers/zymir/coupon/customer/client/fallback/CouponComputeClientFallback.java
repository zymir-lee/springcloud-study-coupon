package pers.zymir.coupon.customer.client.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.zymir.compute.model.req.CouponDiscountComputeReq;
import pers.zymir.compute.model.res.CouponComputeRes;
import pers.zymir.coupon.customer.client.CouponComputeClient;

@Component
@Slf4j
public class CouponComputeClientFallback implements CouponComputeClient {
    @Override
    public CouponComputeRes compute(CouponDiscountComputeReq couponDiscountComputeReq) {
        log.warn("调用计算服务发生错误，执行fallback逻辑");
        return new CouponComputeRes();
    }
}
