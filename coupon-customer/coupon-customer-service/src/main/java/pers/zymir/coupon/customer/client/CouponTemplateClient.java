package pers.zymir.coupon.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pers.zymir.coupon.template.model.CouponTemplate;

import java.util.List;

@FeignClient(name = "coupon-template", path = "/coupon-template")
public interface CouponTemplateClient {

    @GetMapping("/{id}")
    CouponTemplate getCouponTemplate(@PathVariable("id") long couponTemplateId);

    @PostMapping("/list")
    List<CouponTemplate> listCouponTemplates(@RequestBody List<Long> couponTemplateIds);
}
