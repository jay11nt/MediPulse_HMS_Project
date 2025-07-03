package com.ho.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecordResponseDTO {

    private Long id;

    private String diagnosis;

    private String treatment;

    private String prescription;

    private LocalDate recordDate;

    private Long patientId;

    private Long doctorId;

    private List<PrescriptionResponseDTO> prescriptions; // Nested DTOs
}
