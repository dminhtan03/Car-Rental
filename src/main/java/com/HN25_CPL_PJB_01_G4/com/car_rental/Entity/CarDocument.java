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
@Table(name = "CarDocument", indexes = {
    @Index(name = "idx_car_doc_car", columnList = "car_id"),
    @Index(name = "idx_car_doc_status", columnList = "verification_status")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long docId;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    @NotNull(message = "Car is required")
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Document type is required")
    private DocumentType documentType;

    @NotBlank(message = "Document name is required")
    @Size(max = 255, message = "Document name too long")
    @Pattern(regexp = ".*\\.(doc|docx|pdf|jpg|jpeg|png)$", message = "Invalid file type. Allowed types: .doc, .docx, .pdf, .jpg, .jpeg, .png")
    @Column(nullable = false)
    private String documentName;

    @NotBlank(message = "MIME type is required")
    @Pattern(regexp = "^(application/(msword|vnd\\.openxmlformats-officedocument\\.wordprocessingml\\.document|pdf)|image/(jpeg|png))$", 
            message = "Invalid MIME type. Allowed types: application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, application/pdf, image/jpeg, image/png")
    @Column(nullable = false)
    private String documentTypeMime;

    @NotNull(message = "Document data is required")
    @Size(min = 1, max = 5242880, message = "Document size must be between 1 byte and 5MB")
    @Lob
    @Column(name = "document_data", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] documentData;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    @NotNull(message = "Verification status is required")
    private VerificationStatus verificationStatus;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum DocumentType {
        REGISTRATION, INSPECTION, INSURANCE
    }

    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED
    }
}
