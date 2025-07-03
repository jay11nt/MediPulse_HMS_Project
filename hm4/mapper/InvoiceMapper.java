package com.ho.mapper;

import com.ho.dto.request.InvoiceRequestDTO;
import com.ho.dto.response.InvoiceResponseDTO;
import com.ho.entity.Appointment;
import com.ho.entity.Invoice;
import com.ho.entity.Patient;

import lombok.experimental.UtilityClass;

@UtilityClass // Makes all methods static
public class InvoiceMapper {

    //  Convert RequestDTO to Entity
    public Invoice toEntity(InvoiceRequestDTO dto, Patient patient, Appointment appointment) {
        if (dto == null) {
            return null;
        }

        return Invoice.builder()
                .invoiceDate(dto.getInvoiceDate())
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .status(Invoice.InvoiceStatus.valueOf(dto.getStatus().toUpperCase()))
                .description(dto.getDescription())
                .patient(patient)
                .appointment(appointment)
                .build();
    }

    //  Convert Entity to ResponseDTO
    public InvoiceResponseDTO toResponseDTO(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        return InvoiceResponseDTO.builder()
                .id(invoice.getId())
                .invoiceDate(invoice.getInvoiceDate())
                .amount(invoice.getAmount())
                .paymentMethod(invoice.getPaymentMethod())
                .status(invoice.getStatus().name())
                .description(invoice.getDescription())
                .patientId(invoice.getPatient() != null ? invoice.getPatient().getId() : null)
                .appointmentId(invoice.getAppointment() != null ? invoice.getAppointment().getId() : null)
                .build();
    }

    //  Update existing Invoice entity from DTO (for PUT)
    public void updateEntityFromDTO(InvoiceRequestDTO dto, Invoice invoice, Patient patient, Appointment appointment) {
        if (dto == null || invoice == null) return;

        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setAmount(dto.getAmount());
        invoice.setPaymentMethod(dto.getPaymentMethod());
        invoice.setStatus(Invoice.InvoiceStatus.valueOf(dto.getStatus().toUpperCase()));
        invoice.setDescription(dto.getDescription());
        invoice.setPatient(patient);
        invoice.setAppointment(appointment);
    }
}
