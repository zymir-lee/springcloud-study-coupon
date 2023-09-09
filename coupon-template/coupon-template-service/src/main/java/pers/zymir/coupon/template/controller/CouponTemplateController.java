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
    public CouponTemplate getById(@PathVariable("id") long id) {
        return couponTemplateService.getCouponTemplateById(id);
    }

    @PostMapping
    public Map<Long, CouponTemplate> getMapping(@RequestBody List<Long> couponTemplateIds) {
        return couponTemplateService.couponTemplateIdMapping(couponTemplateIds);
    }

    @PostMapping
    public List<CouponTemplate> listCouponTemplates(@RequestBody List<Long> couponTemplateIds) {
        return couponTemplateService.listCouponTemplates(couponTemplateIds);
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
