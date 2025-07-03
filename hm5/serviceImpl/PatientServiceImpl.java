package com.ho.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ho.dto.request.PatientRequestDTO;
import com.ho.dto.response.PatientResponseDTO;
import com.ho.entity.Patient;
import com.ho.entity.Room;
import com.ho.exception.ResourceNotFoundException;
import com.ho.mapper.PatientMapper;
import com.ho.repository.NurseRepository;
import com.ho.repository.PatientRepository;
import com.ho.repository.RoomRepository;
import com.ho.service.PatientService;

import lombok.RequiredArgsConstructor;

@Service								//======= here in IMPL we do kaha se kaha bhejna hai==========
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService 
{

    private final PatientRepository patientRepository;
    private final NurseRepository nurseRepository;
    private final RoomRepository roomRepository;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO dto) 
    {
        Patient patient = PatientMapper.toEntity(dto);
        
        // Load Room from DB and attached (fk always mapped here)
        if (dto.getRoomId() != null) {
             Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", dto.getRoomId()));
            patient.setRoom(room);
        }
   
        Patient saved = patientRepository.save(patient);
        	return PatientMapper.toResponseDTO(saved);
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO dto) 	//used Lamba expression
    {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        PatientMapper.updateEntityFromDTO(dto, existing);
        Patient updated = patientRepository.save(existing);
        	return PatientMapper.toResponseDTO(updated);
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) 
    {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        	return PatientMapper.toResponseDTO(patient);
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() 		//used stream api function (for list of data )
    {
        return patientRepository.findAll().stream()
                .map(PatientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePatient(Long id) 
    {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        patientRepository.delete(patient);
    }


	@Override
	public List<String> getAllNurseNamesByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        return patient.getNurses().stream()
                .map(nurse -> nurse.getFullName())
                .collect(Collectors.toList());
    }
}


//============createPatient() method:===============
//
// 1. Converts requestDTO ➝ Entity  (through mapper)
// 2. Saves to DB
// 3. Converts Entity ➝ ResponseDTO
// 4. Returns clear response

