package pers.zymir.coupon.template.dao;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pers.zymir.coupon.template.CouponTemplateApplication;
import pers.zymir.coupon.template.model.CouponTemplate;

@SpringBootTest
@ContextConfiguration(classes = CouponTemplateApplication.class)
@RunWith(SpringRunner.class)
public class CouponTemplateMapperTest {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;

    @Test
    public void databaseConnectionTest() {
        LambdaQueryWrapper<CouponTemplate> queryWrapper =
                Wrappers.lambdaQuery(CouponTemplate.class).eq(CouponTemplate::getId, 1L);
        CouponTemplate couponTemplate = couponTemplateMapper.selectOne(queryWrapper);
        System.out.println(JSONUtil.toJsonPrettyStr(couponTemplate));
        Assert.assertNotNull(couponTemplate);
    }
}
