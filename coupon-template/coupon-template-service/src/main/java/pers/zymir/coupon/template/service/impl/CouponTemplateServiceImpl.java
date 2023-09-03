package pers.zymir.coupon.template.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zymir.coupon.template.dao.CouponTemplateMapper;
import pers.zymir.coupon.template.dto.CouponTemplateCreateDTO;
import pers.zymir.coupon.template.model.CouponTemplate;
import pers.zymir.coupon.template.service.ICouponTemplateService;
import pers.zymir.util.sql.MybatisHelper;

@Service
public class CouponTemplateServiceImpl implements ICouponTemplateService {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;

    @Override
    public boolean createCouponTemplate(CouponTemplateCreateDTO couponTemplateCreateDTO) {
        CouponTemplate couponTemplate = BeanUtil.toBean(couponTemplateCreateDTO, CouponTemplate.class);
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
}
