package com.ho.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ho.dto.request.NurseRequestDTO;
import com.ho.dto.response.NurseResponseDTO;
import com.ho.entity.Nurse;
import com.ho.entity.Patient;
import com.ho.exception.ResourceNotFoundException;
import com.ho.mapper.NurseMapper;
import com.ho.repository.NurseRepository;
import com.ho.repository.PatientRepository;
import com.ho.service.NurseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NurseServiceImpl implements NurseService {

    private final NurseRepository nurseRepository;
    private final PatientRepository patientRepository;

    @Override
    public NurseResponseDTO createNurse(NurseRequestDTO dto) {
        List<Patient> patients = dto.getPatientIds() != null
                ? dto.getPatientIds().stream()
                        .map(id -> patientRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id)))
                        .collect(Collectors.toList())
                : List.of();

        Nurse nurse = NurseMapper.toEntity(dto, patients);
        Nurse saved = nurseRepository.save(nurse);
        return NurseMapper.toResponseDTO(saved);
    }

    @Override
    public NurseResponseDTO updateNurse(Long id, NurseRequestDTO dto) {
        Nurse existing = nurseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nurse", "id", id));

        NurseMapper.updateEntityFromDTO(dto, existing);

        if (dto.getPatientIds() != null) {
            List<Patient> patients = dto.getPatientIds().stream()
                    .map(pid -> patientRepository.findById(pid)
                            .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", pid)))
                    .collect(Collectors.toList());
            existing.setPatient(patients);
        }

        Nurse updated = nurseRepository.save(existing);
        return NurseMapper.toResponseDTO(updated);
    }

    @Override
    public NurseResponseDTO getNurseById(Long id) {
        Nurse nurse = nurseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nurse", "id", id));
        return NurseMapper.toResponseDTO(nurse);
    }

    @Override
    public List<NurseResponseDTO> getAllNurses() {
        return nurseRepository.findAll()
                .stream()
                .map(NurseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNurse(Long id) {
        Nurse nurse = nurseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nurse", "id", id));
        nurseRepository.delete(nurse);
    }

    @Override
    public void assignPatientToNurse(Long nurseId, Long patientId) {
        Nurse nurse = nurseRepository.findById(nurseId)
                .orElseThrow(() -> new ResourceNotFoundException("Nurse", "id", nurseId));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        nurse.getPatient().add(patient);
        nurseRepository.save(nurse);
    }
}
