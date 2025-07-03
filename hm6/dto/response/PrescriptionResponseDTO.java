package com.ho.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionResponseDTO {

    private Long id;

    private String medicineName;

    private String dosage;

    private String frequency;

    private Integer durationInDays;

    private LocalDateTime prescriptionDateTime;

    private String instructions;

    private Long doctorid;

    private Long patientid;

    private Long medicalRecordId;
}
