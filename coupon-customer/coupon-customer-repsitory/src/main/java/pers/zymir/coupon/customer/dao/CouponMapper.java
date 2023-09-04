package pers.zymir.coupon.customer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.zymir.coupon.customer.model.Coupon;

@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {

}
