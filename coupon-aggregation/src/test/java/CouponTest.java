import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.zymir.coupon.CouponApplication;
import pers.zymir.coupon.compute.dto.CouponComputeDTO;
import pers.zymir.coupon.compute.model.CartProductItem;
import pers.zymir.coupon.compute.model.ShoppingCart;
import pers.zymir.coupon.compute.res.CouponComputeRes;
import pers.zymir.coupon.compute.service.ICouponComputeService;
import pers.zymir.coupon.customer.model.Coupon;
import pers.zymir.coupon.customer.service.ICustomerCouponService;
import pers.zymir.util.stream.StreamUtil;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = CouponApplication.class)
@RunWith(SpringRunner.class)
public class CouponTest {

    @Autowired
    private ICouponComputeService couponComputeService;

    @Autowired
    private ICustomerCouponService customerCouponService;

    @Test
    public void test() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(123L);

        List<CartProductItem> cartProductItems = new ArrayList<>();
        cartProductItems.add(new CartProductItem(1000L, 10L, -1L));
        shoppingCart.setProductItems(cartProductItems);

        List<Coupon> coupons = customerCouponService.queryUserAvailableCoupons(123L);
        List<CouponComputeDTO.CouponInfoDTO> couponInfoDTOS = StreamUtil.mapTo(coupons, c -> {
            CouponComputeDTO.CouponInfoDTO couponInfoDTO = new CouponComputeDTO.CouponInfoDTO();
            couponInfoDTO.setId(c.getId());
            couponInfoDTO.setTemplateId(c.getCouponTemplateId());
            return couponInfoDTO;
        });

        CouponComputeDTO couponComputeDTO = new CouponComputeDTO();
        couponComputeDTO.setShoppingCart(shoppingCart);
        couponComputeDTO.setCoupons(couponInfoDTOS);
        CouponComputeRes compute = couponComputeService.compute(couponComputeDTO);
        System.out.println(JSONUtil.toJsonPrettyStr(compute));
    }
}
