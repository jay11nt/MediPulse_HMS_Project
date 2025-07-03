package com.ho.service;			//========After fetched, (objects) send/map to ReponseDto(process data+ prepare + return final data) =======================

import java.util.List;

import com.ho.dto.request.DoctorRequestDTO;
import com.ho.dto.response.DoctorResponseDTO;
import com.ho.dto.response.PatientResponseDTO;
import com.ho.entity.Appointment;

public interface DoctorService {

    // Creates a new doctor record
    DoctorResponseDTO createDoctor(DoctorRequestDTO dto);

    // Updates an existing doctor's information
    DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO dto);

    // Retrieves a doctor by their ID
    DoctorResponseDTO getDoctorById(Long id);

    // Returns a list of all doctors
    List<DoctorResponseDTO> getAllDoctors();

    // Deletes a doctor record by ID
    void deleteDoctor(Long id);

    // Assigns a patient to a specific doctor
    void assignPatientToDoctor(Long doctorId, Long patientId);

    // Retrieves all patients assigned to a doctor
    List<PatientResponseDTO> getPatientsByDoctor(Long doctorId);

    // Searches doctors by specialization
    List<DoctorResponseDTO> searchDoctorsBySpecialization(String specialization);

    // Retrieves all appointments scheduled for a doctor
    List<Appointment> getAppointmentsForDoctor(Long doctorId);
}
