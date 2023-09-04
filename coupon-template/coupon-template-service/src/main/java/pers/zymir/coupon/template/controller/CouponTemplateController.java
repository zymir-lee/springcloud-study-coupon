package pers.zymir.coupon.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.zymir.basic.result.Resp;
import pers.zymir.coupon.template.dto.CouponTemplateCreateDTO;
import pers.zymir.coupon.template.model.CouponTemplate;
import pers.zymir.coupon.template.service.ICouponTemplateService;

@RestController
@RequestMapping("/coupon-template")
public class CouponTemplateController {

    @Autowired
    private ICouponTemplateService couponTemplateService;

    @GetMapping("/{id}")
    public Resp<CouponTemplate> getById(@PathVariable("id") long id) {
        CouponTemplate couponTemplate = couponTemplateService.getCouponTemplateById(id);
        return Resp.success(couponTemplate);
    }

    @PostMapping
    public Resp<Boolean> createCouponTemplate(@RequestBody CouponTemplateCreateDTO couponTemplateCreateDTO) {
        boolean couponTemplate = couponTemplateService.createCouponTemplate(couponTemplateCreateDTO);
        return Resp.success(couponTemplate);
    }

    @PostMapping("/{id}/clone")
    public Resp<Long> cloneCouponTemplate(@PathVariable long clonedId) {
        Long cloneCouponTemplateId = couponTemplateService.cloneCouponTemplate(clonedId);
        return Resp.success(cloneCouponTemplateId);
    }

    @DeleteMapping("/{id}")
    public Resp<Boolean> removeCouponTemplate(@PathVariable long id) {
        boolean removeResult = couponTemplateService.removeCouponTemplate(id);
        return Resp.success(removeResult);
    }
}
