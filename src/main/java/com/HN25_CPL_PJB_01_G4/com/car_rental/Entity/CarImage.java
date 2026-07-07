package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CarImage", indexes = {
    @Index(name = "idx_car_image_car", columnList = "car_id"),
    @Index(name = "idx_car_image_type", columnList = "image_type")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    @NotNull(message = "Car is required")
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type", nullable = false)
    @NotNull(message = "Image type is required")
    private ImageType imageType;

    @NotBlank(message = "Image name is required")
    @Size(max = 255, message = "Image name too long")
    @Pattern(regexp = ".*\\.(jpg|jpeg|png|gif)$", message = "Invalid file type. Allowed types: .jpg, .jpeg, .png, .gif")
    @Column(nullable = false)
    private String imageName;

    @NotBlank(message = "MIME type is required")
    @Pattern(regexp = "^image/(jpeg|png|gif)$", message = "Invalid image MIME type. Allowed types: image/jpeg, image/png, image/gif")
    @Column(nullable = false)
    private String imageTypeMime;

    @NotNull(message = "Image data is required")
    @Size(min = 1, max = 5242880, message = "Image size must be between 1 byte and 5MB")
    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] imageData;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum ImageType {
        FRONT, BACK, LEFT, RIGHT
    }
}
