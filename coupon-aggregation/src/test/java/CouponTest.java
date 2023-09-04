import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.zymir.coupon.CouponApplication;
import pers.zymir.coupon.compute.model.CartProductItem;
import pers.zymir.coupon.compute.model.ShoppingCart;
import pers.zymir.coupon.compute.res.CouponComputeRes;
import pers.zymir.coupon.compute.service.ICouponComputeService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = CouponApplication.class)
@RunWith(SpringRunner.class)
public class CouponTest {

    @Autowired
    private ICouponComputeService couponComputeService;

    @Test
    public void test() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(123L);

        List<CartProductItem> cartProductItems = new ArrayList<>();
        cartProductItems.add(new CartProductItem(1000L, 10L, -1L));
        shoppingCart.setProductItems(cartProductItems);
        CouponComputeRes compute = couponComputeService.compute(shoppingCart);
        System.out.println(JSONUtil.toJsonPrettyStr(compute));
    }
}
