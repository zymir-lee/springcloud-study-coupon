package pers.zymir.coupon.template.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum CouponTypeEnum {

    UNKNOWN(0, "未知"),
    MONEY_OFF(1, "满减"),
    DISCOUNT(2, "打折"),
    RANDOM_DISCOUNT(3, "随机立减"),
    DOUBLE_DISCOUNT_NIGHT(4, "晚间双倍券");

    private final Integer code;

    private final String description;

    public static CouponTypeEnum getCouponType(int code) {
        return Arrays.stream(values())
                .filter(t -> Objects.equals(t.code, code))
                .findFirst().orElse(UNKNOWN);
    }
}
