package pers.zymir.compute.model.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class CouponTemplateDTO {
  private Long couponTemplateId;
  private Long shopId;
  private Long quota;
  private Long threshold;
  private Integer couponType;

  public boolean isLimitShop() {
    return Objects.nonNull(shopId);
  }

  public boolean canUseForShop(Long shopId) {
    return Objects.isNull(shopId) || Objects.equals(shopId, this.shopId);
  }
}