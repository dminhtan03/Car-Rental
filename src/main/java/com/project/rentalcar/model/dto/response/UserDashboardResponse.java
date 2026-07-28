package com.project.rentalcar.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDashboardResponse {

    private UserResponse profile;

    private long totalCarsOwned;

    private long totalBookingsAsCustomer;

    private long totalBookingsAsOwner;

    private long totalFavorites;

    private long unreadNotifications;

    private BigDecimal walletBalance;
}