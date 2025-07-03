package com.ho.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceRequestDTO {

    @NotNull(message = "Invoice date is required")
    private LocalDateTime invoiceDate;

    @NotNull(message = "Amount is required")
    private Double amount;

    private String paymentMethod; // Optional: Cash, UPI, etc.

    @NotNull(message = "Status must be specified")
    private String status; // Enum as String: PAID, UNPAID, HALFPAID

    private String description;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    private Long appointmentId; // Optional
}
