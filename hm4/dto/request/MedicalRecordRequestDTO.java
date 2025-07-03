package com.ho.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecordRequestDTO {

    @NotNull(message = "Diagnosis is required")
    @Size(min = 5, max = 200, message = "Diagnosis should be between 5 to 200 characters")
    private String diagnosis;

    @NotNull(message = "Treatment is required")
    @Size(min = 5, max = 200, message = "Treatment should be between 5 to 200 characters")
    private String treatment;

    @NotNull(message = "Prescription is required")
    @Size(min = 5, max = 200, message = "Prescription should be between 5 to 200 characters")
    private String prescription;

    @NotNull(message = "Record date is required")
    private LocalDate recordDate;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;
}
