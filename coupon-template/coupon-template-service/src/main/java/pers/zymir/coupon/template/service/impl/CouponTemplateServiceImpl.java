package pers.zymir.coupon.template.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zymir.coupon.template.dao.CouponTemplateMapper;
import pers.zymir.coupon.template.model.CouponTemplate;
import pers.zymir.coupon.template.model.req.CouponTemplateCreateReq;
import pers.zymir.coupon.template.service.ICouponTemplateService;
import pers.zymir.util.sql.MybatisHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CouponTemplateServiceImpl implements ICouponTemplateService {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;

    @Override
    public boolean createCouponTemplate(CouponTemplateCreateReq couponTemplateCreateReq) {
        CouponTemplate couponTemplate = BeanUtil.toBean(couponTemplateCreateReq, CouponTemplate.class);
        int result = couponTemplateMapper.insert(couponTemplate);
        return MybatisHelper.executeSuccess(result);
    }

    @Override
    public boolean removeCouponTemplate(long couponTemplateId) {
        int result = couponTemplateMapper.deleteById(couponTemplateId);
        return MybatisHelper.executeSuccess(result);
    }

    @Override
    public Long cloneCouponTemplate(long couponTemplateId) {
        // TODO 优惠券模版的克隆
        return -1L;
    }

    @Override
    public CouponTemplate getCouponTemplateById(long couponTemplateId) {
        LambdaQueryWrapper<CouponTemplate> queryWrapper =
                Wrappers.lambdaQuery(CouponTemplate.class).eq(CouponTemplate::getId, couponTemplateId);
        return couponTemplateMapper.selectOne(queryWrapper);
    }

    @Override
    public Map<Long, CouponTemplate> couponTemplateIdMapping(List<Long> couponTemplateIds) {
        if (CollUtil.isEmpty(couponTemplateIds)) {
            return new HashMap<>();
        }

        return listCouponTemplates(couponTemplateIds).stream().collect(Collectors.toMap(CouponTemplate::getId, Function.identity()));
    }

    @Override
    public List<CouponTemplate> listCouponTemplates(List<Long> couponTemplateIds) {
        if (CollUtil.isEmpty(couponTemplateIds)) {
            return new ArrayList<>();
        }

        LambdaQueryWrapper<CouponTemplate> queryWrapper = Wrappers.lambdaQuery(CouponTemplate.class).in(CouponTemplate::getId, couponTemplateIds);
        return couponTemplateMapper.selectList(queryWrapper);
    }
}
