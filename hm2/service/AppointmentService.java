package com.ho.service;

import java.time.LocalDate;
import java.util.List;

import com.ho.dto.request.AppointmentRequestDTO;
import com.ho.dto.response.AppointmentResponseDTO;

public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto);

    AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO dto);

    AppointmentResponseDTO getAppointmentById(Long id);

    List<AppointmentResponseDTO> getAllAppointments();

    void deleteAppointment(Long id);

    List<AppointmentResponseDTO> getAppointmentsByPatientId(Long patientId);

    List<AppointmentResponseDTO> getAppointmentsByDoctorId(Long doctorId);

    List<AppointmentResponseDTO> getAppointmentsByDate(LocalDate date);

	AppointmentResponseDTO updatedAppointment(Long id, AppointmentRequestDTO dto);
}
