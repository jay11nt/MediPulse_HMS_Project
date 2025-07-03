package com.ho.service;

import java.util.List;

import com.ho.dto.request.InvoiceRequestDTO;
import com.ho.dto.response.InvoiceResponseDTO;

public interface InvoiceService {

    InvoiceResponseDTO createInvoice(InvoiceRequestDTO dto);

    InvoiceResponseDTO updateInvoice(Long id, InvoiceRequestDTO dto);

    InvoiceResponseDTO getInvoiceById(Long id);

    List<InvoiceResponseDTO> getAllInvoices();

    void deleteInvoice(Long id);

    List<InvoiceResponseDTO> getInvoicesByPatientId(Long patientId);

    InvoiceResponseDTO generateInvoiceForAppointment(Long appointmentId);
}
