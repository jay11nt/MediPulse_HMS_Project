package com.ho.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponseDTO {

    private Long id;

    private LocalDateTime invoiceDate;

    private Double amount;

    private String paymentMethod;

    private String status;

    private String description;

    private Long patientId;

    private Long appointmentId; // May be null if not linked
}
