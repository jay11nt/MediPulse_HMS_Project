package com.ho.dto.response;

import java.time.LocalDateTime;

import com.ho.entity.Appointment.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponseDTO {

    private Long id;

    private LocalDateTime appointmentDateTime;

    private AppointmentStatus status;

    private String remarks;

    private Long patientId;

    private Long doctorId;
}
