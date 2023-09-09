package pers.zymir.coupon.compute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zymir.compute.model.req.CouponDiscountComputeReq;
import pers.zymir.compute.model.res.CouponComputeRes;
import pers.zymir.coupon.compute.service.ICouponComputeService;

@RestController
@RequestMapping("/coupon-compute")
public class CouponComputeController {

  @Autowired
  private ICouponComputeService computeService;

  @PostMapping
  public CouponComputeRes compute(@RequestBody CouponDiscountComputeReq couponDiscountComputeReq) {
    return computeService.compute(couponDiscountComputeReq);
  }
}
