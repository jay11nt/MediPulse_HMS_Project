package com.ho.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ho.dto.request.InvoiceRequestDTO;
import com.ho.dto.response.InvoiceResponseDTO;
import com.ho.entity.Appointment;
import com.ho.entity.Doctor;
import com.ho.entity.Invoice;
import com.ho.entity.Patient;
import com.ho.exception.ResourceNotFoundException;
import com.ho.mapper.InvoiceMapper;
import com.ho.repository.AppointmentRepository;
import com.ho.repository.InvoiceRepository;
import com.ho.repository.PatientRepository;
import com.ho.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Override
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dto.getPatientId()));

        Appointment appointment = null;
        if (dto.getAppointmentId() != null) {
            appointment = appointmentRepository.findById(dto.getAppointmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", dto.getAppointmentId()));
        }

        Invoice invoice = InvoiceMapper.toEntity(dto, patient, appointment);
        Invoice saved = invoiceRepository.save(invoice);
        return InvoiceMapper.toResponseDTO(saved);
    }

    @Override
    public InvoiceResponseDTO updateInvoice(Long id, InvoiceRequestDTO dto) {
        Invoice existing = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", id));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dto.getPatientId()));

        Appointment appointment = null;
        if (dto.getAppointmentId() != null) {
            appointment = appointmentRepository.findById(dto.getAppointmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", dto.getAppointmentId()));
        }

        InvoiceMapper.updateEntityFromDTO(dto, existing, patient, appointment);
        Invoice updated = invoiceRepository.save(existing);
        return InvoiceMapper.toResponseDTO(updated);
    }

    @Override
    public InvoiceResponseDTO getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", id));
        return InvoiceMapper.toResponseDTO(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(InvoiceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteInvoice(Long id) {
        Invoice existing = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", id));
        invoiceRepository.delete(existing);
    }

    @Override
    public List<InvoiceResponseDTO> getInvoicesByPatientId(Long patientId) {
        return invoiceRepository.findAll().stream()
                .filter(inv -> inv.getPatient() != null && inv.getPatient().getId().equals(patientId))
                .map(InvoiceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceResponseDTO generateInvoiceForAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));

        Doctor doctor = appointment.getDoctor();
        Patient patient = appointment.getPatient();

        Invoice invoice = Invoice.builder()
                .amount(doctor.getConsultationFee())
                .invoiceDate(LocalDateTime.now())
                .status(Invoice.InvoiceStatus.UNPAID)
                .description("Consultation fee for appointment on " + appointment.getAppointmentDateTime().toLocalDate())
                .patient(patient)
                .appointment(appointment)
                .build();

        Invoice saved = invoiceRepository.save(invoice);
        return InvoiceMapper.toResponseDTO(saved);
    }
}
