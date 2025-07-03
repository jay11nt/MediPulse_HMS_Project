package com.ho.mapper;

import com.ho.dto.request.AppointmentRequestDTO;
import com.ho.dto.response.AppointmentResponseDTO;
import com.ho.entity.Appointment;
import com.ho.entity.Doctor;
import com.ho.entity.Patient;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppointmentMapper {

    // Convert from DTO to Entity (used in POST or PUT)
    public Appointment toEntity(AppointmentRequestDTO dto, Patient patient, Doctor doctor) {
        if (dto == null) return null;

        return Appointment.builder()
                .appointmentDateTime(dto.getAppointmentDateTime())
                .status(dto.getStatus())
                .remarks(dto.getRemarks())
                .patient(patient)
                .doctor(doctor)
                .build();
    }

    // Convert from Entity to DTO (used in GET)
    public AppointmentResponseDTO toResponseDTO(Appointment appointment) {
        if (appointment == null) return null;

        return AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .appointmentDateTime(appointment.getAppointmentDateTime())
                .status(appointment.getStatus())
                .remarks(appointment.getRemarks())
                .doctorId(appointment.getDoctor() != null ? appointment.getDoctor().getId() : null)
                .patientId(appointment.getPatient() != null ? appointment.getPatient().getId() : null)
                .build();
    }

    // Optional: update entity fields (used in PUT)
    public void updateEntityFromDTO(AppointmentRequestDTO dto, Appointment appointment) {
        if (dto == null || appointment == null) return;

        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
        appointment.setStatus(dto.getStatus());
        appointment.setRemarks(dto.getRemarks());
    }
}
