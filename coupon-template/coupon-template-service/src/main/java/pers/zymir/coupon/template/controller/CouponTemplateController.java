package pers.zymir.coupon.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.zymir.basic.result.Resp;
import pers.zymir.coupon.template.model.CouponTemplate;
import pers.zymir.coupon.template.model.req.CouponTemplateCreateReq;
import pers.zymir.coupon.template.service.ICouponTemplateService;

import java.util.List;
import java.util.Map;

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
    public Resp<Map<Long, CouponTemplate>> getMapping(@RequestBody List<Long> couponTemplateIds) {
        Map<Long, CouponTemplate> couponTemplateMapping = couponTemplateService.couponTemplateIdMapping(couponTemplateIds);
        return Resp.success(couponTemplateMapping);
    }

    @PostMapping
    public Resp<Boolean> createCouponTemplate(@RequestBody CouponTemplateCreateReq couponTemplateCreateReq) {
        boolean couponTemplate = couponTemplateService.createCouponTemplate(couponTemplateCreateReq);
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
