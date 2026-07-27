package com.project.rentalcar.model.entity;

import com.project.rentalcar.common.enums.CouponStatus;
import com.project.rentalcar.common.enums.DiscountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_coupon")
public class Coupon {

    @Id
    private String id;

    @Column(name = "CODE", unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "DISCOUNT_TYPE")
    private DiscountType discountType;

    @Column(name = "DISCOUNT_VALUE", precision = 15, scale = 2)
    private BigDecimal discountValue;

    @Column(name = "MINIMUM_AMOUNT", precision = 15, scale = 2)
    private BigDecimal minimumAmount;

    /**
     * Giảm tối đa bao nhiêu tiền
     * (chỉ áp dụng cho PERCENT)
     */
    @Column(name = "MAX_DISCOUNT", precision = 15, scale = 2)
    private BigDecimal maxDiscount;

    @Column(name = "EXPIRED_AT")
    private LocalDateTime expiredAt;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "USED_QUANTITY")
    private Integer usedQuantity = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private CouponStatus status;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

}
