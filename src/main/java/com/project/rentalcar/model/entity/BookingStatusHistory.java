package com.project.rentalcar.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.rentalcar.common.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_booking_status_history")
public class BookingStatusHistory {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(name = "OLD_STATUS")
    private BookingStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "NEW_STATUS")
    private BookingStatus newStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHANGED_BY")
    private User changedBy;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "CHANGED_AT")
    private LocalDateTime changedAt;

}
