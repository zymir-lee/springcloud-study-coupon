package pers.zymir.coupon.compute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zymir.basic.result.Resp;
import pers.zymir.coupon.compute.dto.CouponComputeDTO;
import pers.zymir.coupon.compute.res.CouponComputeRes;
import pers.zymir.coupon.compute.service.ICouponComputeService;

@RestController
@RequestMapping("/coupon-compute")
public class CouponComputeController {

  @Autowired
  private ICouponComputeService computeService;

  @PostMapping
  public Resp<CouponComputeRes> compute(@RequestBody CouponComputeDTO couponComputeDTO) {
    CouponComputeRes compute = computeService.compute(couponComputeDTO);
    return Resp.success(compute);
  }
}
