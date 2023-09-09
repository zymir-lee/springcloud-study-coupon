package pers.zymir.coupon.customer.service;

import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.zymir.coupon.customer.dto.ProductItemDTO;
import pers.zymir.coupon.customer.req.CustomerCouponDiscountComputeReq;
import pers.zymir.coupon.customer.res.CustomerCouponDiscountComputeRes;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerCouponServiceTest {

    @Autowired
    private ICustomerCouponService customerCouponService;

    @Test
    public void test() {

        CustomerCouponDiscountComputeReq computeRequest = new CustomerCouponDiscountComputeReq();
        computeRequest.setUserId(123L);

        List<ProductItemDTO> productItems = new ArrayList<>();
        ProductItemDTO productItemDTO = new ProductItemDTO();
        productItemDTO.setCount(10L);
        productItemDTO.setPrice(130000L);
        productItemDTO.setShopId(-1L);
        productItems.add(productItemDTO);
        computeRequest.setProductItems(productItems);

        CustomerCouponDiscountComputeRes computeRes = customerCouponService.computeCouponDiscount(computeRequest);
        System.out.println(JSONUtil.toJsonPrettyStr(computeRes));
    }
}
