package com.ho.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ho.dto.request.InvoiceRequestDTO;
import com.ho.dto.response.InvoiceResponseDTO;
import com.ho.service.InvoiceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    //  Manually Create a new Invoice
    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@Valid @RequestBody InvoiceRequestDTO dto) {
        InvoiceResponseDTO created = invoiceService.createInvoice(dto);
        return ResponseEntity.ok(created);
    }

    //  Automatically generate Invoice for an Appointment
    @PostMapping("/generate/{appointmentId}")
    public ResponseEntity<InvoiceResponseDTO> generateInvoice(@PathVariable Long appointmentId) {
        InvoiceResponseDTO invoice = invoiceService.generateInvoiceForAppointment(appointmentId);
        return ResponseEntity.ok(invoice);
    }

    //  Update an existing Invoice
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceRequestDTO dto) {
        InvoiceResponseDTO updated = invoiceService.updateInvoice(id, dto);
        return ResponseEntity.ok(updated);
    }

    //  Get Invoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(@PathVariable Long id) {
        InvoiceResponseDTO invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    //  Get all Invoices
    @GetMapping("/getall")
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoices() {
        List<InvoiceResponseDTO> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    //  Delete Invoice by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    //  Get all Invoices for a specific Patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<InvoiceResponseDTO>> getInvoicesByPatientId(@PathVariable Long patientId) {
        List<InvoiceResponseDTO> invoices = invoiceService.getInvoicesByPatientId(patientId);
        return ResponseEntity.ok(invoices);
    }
}
