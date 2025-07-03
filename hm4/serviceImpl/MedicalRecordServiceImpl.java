package com.ho.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ho.dto.request.MedicalRecordRequestDTO;
import com.ho.dto.response.MedicalRecordResponseDTO;
import com.ho.entity.Doctor;
import com.ho.entity.MedicalRecord;
import com.ho.entity.Patient;
import com.ho.exception.ResourceNotFoundException;
import com.ho.mapper.MedicalRecordMapper;
import com.ho.repository.DoctorRepository;
import com.ho.repository.MedicalRecordRepository;
import com.ho.repository.PatientRepository;
import com.ho.service.MedicalRecordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public MedicalRecordResponseDTO createMedicalRecord(MedicalRecordRequestDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", dto.getDoctorId()));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dto.getPatientId()));

        MedicalRecord record = MedicalRecordMapper.toEntity(dto, patient, doctor);
        MedicalRecord saved = medicalRecordRepository.save(record);

        return MedicalRecordMapper.toResponseDTO(saved);
    }

    @Override
    public MedicalRecordResponseDTO updateMedicalRecord(Long id, MedicalRecordRequestDTO dto) {
        MedicalRecord existing = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "id", id));

        MedicalRecordMapper.updateEntityFromDTO(dto, existing);

        // Optionally update doctor and patient if needed (PUT)
        if (dto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", dto.getDoctorId()));
            existing.setDoctor(doctor);
        }

        if (dto.getPatientId() != null) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dto.getPatientId()));
            existing.setPatient(patient);
        }

        MedicalRecord updated = medicalRecordRepository.save(existing);
        return MedicalRecordMapper.toResponseDTO(updated);
    }

    @Override
    public MedicalRecordResponseDTO getMedicalRecordById(Long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "id", id));
        return MedicalRecordMapper.toResponseDTO(record);
    }

    @Override
    public List<MedicalRecordResponseDTO> getAllMedicalRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(MedicalRecordMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMedicalRecord(Long id) {
        MedicalRecord existing = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "id", id));
        medicalRecordRepository.delete(existing);
    }

    @Override
    public List<MedicalRecordResponseDTO> getRecordsByPatientId(Long patientId) {
        return medicalRecordRepository.findAll().stream()
                .filter(record -> record.getPatient().getId().equals(patientId))
                .map(MedicalRecordMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
