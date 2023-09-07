package pers.zymir.coupon.template.service;

import pers.zymir.coupon.template.model.CouponTemplate;
import pers.zymir.coupon.template.model.req.CouponTemplateCreateReq;

import java.util.List;
import java.util.Map;

public interface ICouponTemplateService {
    boolean createCouponTemplate(CouponTemplateCreateReq couponTemplateCreateReq);

    boolean removeCouponTemplate(long couponTemplateId);

    Long cloneCouponTemplate(long couponTemplateId);

    CouponTemplate getCouponTemplateById(long couponTemplateId);

    Map<Long, CouponTemplate> couponTemplateIdMapping(List<Long> couponTemplateIds);
}
