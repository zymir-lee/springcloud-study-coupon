package pers.zymir.coupon.template.model.req;

import lombok.Data;
import pers.zymir.coupon.template.enums.CouponTypeEnum;

@Data
public class CouponTemplateCreateReq {
    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券描述
     */
    private String description;

    /**
     * 优惠券类型
     * {@link CouponTypeEnum#getCode()}
     */
    private Integer couponType;

    /**
     * 适用商店id null表示全店通用
     */
    private Long shopId;

    /**
     * 优惠价格：基于不同的类型有不同策略
     */
    private Long quota;

    /**
     * 使用优惠券金额阈值
     */
    private Long threshold;

    /**
     * 每人限制领取数量
     */
    private Integer takeLimitCount;

    /**
     * 失效时间 时间戳
     */
    private Long deadline;
}
