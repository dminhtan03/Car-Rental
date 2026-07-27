package com.project.rentalcar.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "tbl_review",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "BOOKING_ID")
        }
)
public class Review {

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "COMMENT", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "RECOMMEND")
    private Boolean recommend = true;

    @Column(name = "LIKE_COUNT")
    private Integer likeCount = 0;

    @Column(name = "IS_HIDDEN")
    private Boolean hidden = false;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "review",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private ReviewReply reply;

    @OneToMany(mappedBy = "review",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ReviewImage> images = new ArrayList<>();

}