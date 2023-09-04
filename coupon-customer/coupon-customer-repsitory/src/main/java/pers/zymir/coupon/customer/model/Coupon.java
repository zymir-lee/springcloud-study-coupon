package pers.zymir.coupon.customer.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long couponTemplateId;

    private Long userId;

    private Long shopId;

    private Date createTime;

    private Integer state;

    @Getter
    @AllArgsConstructor
    public enum CouponStateEnum {
        NOT_USED(1),
        USED(2);

        private final Integer code;

        public boolean isUsed(Integer code) {
            // 应对意外的1、2意外的状况
            return !(Objects.equals(code, NOT_USED.code));
        }
    }
}
