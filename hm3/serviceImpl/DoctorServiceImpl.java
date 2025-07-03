package com.ho.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ho.dto.request.DoctorRequestDTO;
import com.ho.dto.response.DoctorResponseDTO;
import com.ho.dto.response.PatientResponseDTO;
import com.ho.entity.Admin;
import com.ho.entity.Appointment;
import com.ho.entity.Doctor;
import com.ho.entity.Patient;
import com.ho.exception.ResourceNotFoundException;
import com.ho.mapper.DoctorMapper;
import com.ho.mapper.PatientMapper;
import com.ho.repository.AdminRepository;
import com.ho.repository.AppointmentRepository;
import com.ho.repository.DoctorRepository;
import com.ho.repository.PatientRepository;
import com.ho.service.DoctorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final AdminRepository adminRepository;

    @Override			// NOTE- here doctorImpl used (Overload mapper logic in mapper) and employeeImpl  not using it both diff. logic both are Acceptable
    public DoctorResponseDTO createDoctor(DoctorRequestDTO dto) 
    { 
    	Admin admin = adminRepository.findById(dto.getAdminId())
    .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", dto.getAdminId()));

    Doctor doctor = DoctorMapper.toEntity(dto, admin);
        Doctor saved = doctorRepository.save(doctor);
        return DoctorMapper.toResponseDTO(saved);
    }

    @Override
    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO dto) 
    {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        DoctorMapper.updateEntityFromDTO(dto, doctor);
        Doctor updated = doctorRepository.save(doctor);
        return DoctorMapper.toResponseDTO(updated);
    }

    @Override
    public DoctorResponseDTO getDoctorById(Long id) 
    {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        return DoctorMapper.toResponseDTO(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() 
    {
        return doctorRepository.findAll().stream()
                .map(DoctorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDoctor(Long id) 
    {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        doctorRepository.delete(doctor);
    }
//----------------CRUD ended----------------------------
    
    
    @Override
    public void assignPatientToDoctor(Long doctorId, Long patientId) 
    {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        patient.getDoctors().add(doctor); // Assuming Patient has List<Doctor> doctors
        doctor.getPatients().add(patient); // Assuming Doctor has List<Patient> patients

        patientRepository.save(patient); // Persist the association
    }

    @Override
    public List<PatientResponseDTO> getPatientsByDoctor(Long doctorId) 
    {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

        return doctor.getPatients().stream()
                .map(PatientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorResponseDTO> searchDoctorsBySpecialization(String specialization) 
    {
        return doctorRepository.findAll().stream()
                .filter(doc -> doc.getSpecialization().name().equalsIgnoreCase(specialization))
                .map(DoctorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(Long doctorId) 
    {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
        return appointmentRepository.findByDoctor(doctor);
    }
}
