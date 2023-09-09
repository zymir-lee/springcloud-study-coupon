package pers.zymir.coupon.customer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zymir.compute.model.dto.CartProductItemDTO;
import pers.zymir.compute.model.dto.CouponTemplateDTO;
import pers.zymir.compute.model.req.CouponDiscountComputeReq;
import pers.zymir.coupon.customer.dao.CouponMapper;
import pers.zymir.coupon.customer.model.Coupon;
import pers.zymir.coupon.customer.req.CustomerCouponDiscountComputeReq;
import pers.zymir.coupon.customer.req.ReceiveCouponReq;
import pers.zymir.coupon.customer.res.CustomerCouponDiscountComputeRes;
import pers.zymir.coupon.customer.service.ICustomerCouponService;
import pers.zymir.coupon.template.model.CouponTemplate;
import pers.zymir.coupon.template.service.ICouponTemplateService;
import pers.zymir.util.sql.MybatisHelper;
import pers.zymir.util.stream.StreamUtil;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CustomerCouponService implements ICustomerCouponService {

    @Autowired
    private ICouponTemplateService couponTemplateService;

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public boolean receiveCoupon(ReceiveCouponReq receiveCouponReq) {
        Long couponTemplateId = receiveCouponReq.getCouponTemplateId();
        // TODO 修改为远程调用
        CouponTemplate couponTemplate = couponTemplateService.getCouponTemplateById(couponTemplateId);
        if (Objects.isNull(couponTemplate)) {
            log.info("领取优惠券失败，优惠券模版不存在，用户ID：{}, 模版ID：{}", receiveCouponReq.getUserId(), couponTemplateId);
            return false;
        }
        if (!couponTemplate.isActive()) {
            log.info("领取优惠券失败，当前优惠券模版未启用，用户ID：{}, 模版ID：{}", receiveCouponReq.getUserId(), couponTemplateId);
            return false;
        }

        Coupon coupon = Coupon.builder()
                .userId(receiveCouponReq.getUserId())
                .couponTemplateId(couponTemplateId)
                .shopId(couponTemplate.getShopId())
                .state(Coupon.CouponStateEnum.NOT_USED.getCode())
                .build();
        int insertResult = couponMapper.insert(coupon);
        return MybatisHelper.executeSuccess(insertResult);
    }

    @Override
    public List<Coupon> queryUserAvailableCoupons(long userId) {
        LambdaQueryWrapper<Coupon> queryWrapper = Wrappers.lambdaQuery(Coupon.class)
                .eq(Coupon::getUserId, userId)
                .eq(Coupon::getState, Coupon.CouponStateEnum.NOT_USED.getCode());
        return couponMapper.selectList(queryWrapper);
    }

    @Override
    public CustomerCouponDiscountComputeRes computeCouponDiscount(CustomerCouponDiscountComputeReq request) {
        List<Coupon> coupons = queryUserAvailableCoupons(request.getUserId());
        if (CollUtil.isEmpty(coupons)) {
            return new CustomerCouponDiscountComputeRes();
        }

        List<Long> couponTemplateIds = StreamUtil.mapTo(coupons, Coupon::getCouponTemplateId);
        // TODO 修改为远程调用
        List<CouponTemplate> couponTemplates = couponTemplateService.listCouponTemplates(couponTemplateIds);
        CouponDiscountComputeReq couponDiscountComputeReq = new CouponDiscountComputeReq();
        couponDiscountComputeReq.setCouponTemplates(BeanUtil.copyToList(couponTemplates, CouponTemplateDTO.class));
        couponDiscountComputeReq.setUserId(request.getUserId());
        couponDiscountComputeReq.setProductItems(BeanUtil.copyToList(request.getProductItems(), CartProductItemDTO.class));

        // TODO 远程调用compute服务
        return null;
    }
}
