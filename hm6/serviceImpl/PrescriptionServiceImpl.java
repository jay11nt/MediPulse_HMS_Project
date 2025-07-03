package com.ho.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ho.dto.request.PrescriptionRequestDTO;
import com.ho.dto.response.PrescriptionResponseDTO;
import com.ho.entity.Doctor;
import com.ho.entity.MedicalRecord;
import com.ho.entity.Patient;
import com.ho.entity.Prescription;
import com.ho.exception.ResourceNotFoundException;
import com.ho.mapper.PrescriptionMapper;
import com.ho.repository.DoctorRepository;
import com.ho.repository.MedicalRecordRepository;
import com.ho.repository.PatientRepository;
import com.ho.repository.PrescriptionRepository;
import com.ho.service.PrescriptionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public PrescriptionResponseDTO createPrescription(PrescriptionRequestDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", dto.getDoctorId()));
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dto.getPatientId()));
        MedicalRecord medicalRecord = medicalRecordRepository.findById(dto.getMedicalRecordId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "id", dto.getMedicalRecordId()));

        Prescription prescription = PrescriptionMapper.toEntity(dto, doctor, patient, medicalRecord);
        Prescription saved = prescriptionRepository.save(prescription);
        return PrescriptionMapper.toResponseDTO(saved);
    }

    @Override
    public PrescriptionResponseDTO updatePrescription(Long id, PrescriptionRequestDTO dto) {
        Prescription existing = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", id));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", dto.getDoctorId()));
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dto.getPatientId()));
        MedicalRecord medicalRecord = medicalRecordRepository.findById(dto.getMedicalRecordId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "id", dto.getMedicalRecordId()));

        PrescriptionMapper.updateEntityFromDTO(dto, existing, doctor, patient, medicalRecord);
        Prescription updated = prescriptionRepository.save(existing);
        return PrescriptionMapper.toResponseDTO(updated);
    }

    @Override
    public PrescriptionResponseDTO getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", id));
        return PrescriptionMapper.toResponseDTO(prescription);
    }

    @Override
    public List<PrescriptionResponseDTO> getAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
                .map(PrescriptionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePrescription(Long id) {
        Prescription existing = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", id));
        prescriptionRepository.delete(existing);
    }
}
