package com.project.rentalcar.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.rentalcar.common.enums.CarDocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_car_document")
public class CarDocument {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(name = "DOCUMENT_TYPE")
    private CarDocumentType documentType;

    @Column(name = "DOCUMENT_URL")
    private String documentUrl;

    @Column(name = "VERIFIED")
    private Boolean verified = false;

    @Column(name = "VERIFIED_AT")
    private LocalDateTime verifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VERIFIED_BY")
    private User verifiedBy;

}
