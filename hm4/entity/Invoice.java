package com.ho.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Invoice date is required")
    private LocalDateTime invoiceDate;

    @NotNull(message = "Amount is required")
    private Double amount;

    private String paymentMethod; // e.g. Cash, Credit Card, Debit card, UPI, Insurance

    public enum InvoiceStatus{PAID, UNPAID, HALFPAID}
    @Enumerated(EnumType.STRING)
    @NotNull(message = "status must be checked")
    private InvoiceStatus status; 

    private String description; // Optional notes about the billing

		    // Mapping with Patient (Many invoices for one patient)
		    @ManyToOne
		    @JoinColumn(name = "patient_id")
		    private Patient patient;
		
		    // Optional: Mapping to Appointment (invoice may be linked to an appointment)
		    @OneToOne
		    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
		    private Appointment appointment;
}
