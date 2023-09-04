package pers.zymir.coupon.template.service;

import pers.zymir.coupon.template.dto.CouponTemplateCreateDTO;
import pers.zymir.coupon.template.model.CouponTemplate;

import java.util.List;
import java.util.Map;

public interface ICouponTemplateService {
    boolean createCouponTemplate(CouponTemplateCreateDTO couponTemplateCreateDTO);

    boolean removeCouponTemplate(long couponTemplateId);

    Long cloneCouponTemplate(long couponTemplateId);

    CouponTemplate getCouponTemplateById(long couponTemplateId);

    Map<Long, CouponTemplate> couponTemplateIdMapping(List<Long> couponTemplateIds);
}
