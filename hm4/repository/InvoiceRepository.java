package com.ho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Invoice;
import com.ho.entity.Invoice.InvoiceStatus;
import com.ho.entity.Patient;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // Get all invoices for a specific patient
    List<Invoice> findByPatient(Patient patient);

    // Check if an invoice exists for a specific patient
    boolean existsByPatient(Patient patient);

    // Optional: Find invoices by payment status (if such a field exists)
    List<Invoice> findByStatus(String status);
    
    boolean existsByStatus(InvoiceStatus status);

    // Optional: Find invoices within a date range (if your Invoice has a createdDate field)
    // List<Invoice> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
}
