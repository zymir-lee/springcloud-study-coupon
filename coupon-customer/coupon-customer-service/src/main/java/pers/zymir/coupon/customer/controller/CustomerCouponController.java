package pers.zymir.coupon.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zymir.basic.result.Resp;
import pers.zymir.coupon.customer.req.CustomerCouponDiscountComputeReq;
import pers.zymir.coupon.customer.req.ReceiveCouponReq;
import pers.zymir.coupon.customer.res.CustomerCouponDiscountComputeRes;
import pers.zymir.coupon.customer.service.ICustomerCouponService;

@RestController
@RequestMapping("/coupon-customer/")
public class CustomerCouponController {

    @Autowired
    private ICustomerCouponService customerCouponService;

    @PostMapping("/receive")
    public Resp<Boolean> receive(@RequestBody ReceiveCouponReq receiveCouponReq) {
        boolean result = customerCouponService.receiveCoupon(receiveCouponReq);
        return Resp.success(result);
    }

    @PostMapping("/compute")
    public Resp<CustomerCouponDiscountComputeRes> computeShoppingCartDiscount(@RequestBody CustomerCouponDiscountComputeReq request) {
        CustomerCouponDiscountComputeRes customerCouponDiscountComputeRes = customerCouponService.computeCouponDiscount(request);
        return Resp.success(customerCouponDiscountComputeRes);
    }
}
