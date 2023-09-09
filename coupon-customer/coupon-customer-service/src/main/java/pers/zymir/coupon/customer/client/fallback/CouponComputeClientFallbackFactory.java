package pers.zymir.coupon.customer.client.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import pers.zymir.compute.model.req.CouponDiscountComputeReq;
import pers.zymir.compute.model.res.CouponComputeRes;
import pers.zymir.coupon.customer.client.CouponComputeClient;

@Component
@Slf4j
public class CouponComputeClientFallbackFactory implements FallbackFactory<CouponComputeClient> {
    @Override
    public CouponComputeClient create(Throwable cause) {
        return new CouponComputeClient() {
            @Override
            public CouponComputeRes compute(CouponDiscountComputeReq couponDiscountComputeReq) {
                log.warn("调用计算服务发生错误，执行fallback factory逻辑, 异常信息：", cause);
                return new CouponComputeRes();
            }
        };
    }
}
