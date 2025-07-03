package com.ho.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ho.dto.request.MedicalRecordRequestDTO;
import com.ho.dto.response.MedicalRecordResponseDTO;
import com.ho.dto.response.PrescriptionResponseDTO;
import com.ho.entity.Doctor;
import com.ho.entity.MedicalRecord;
import com.ho.entity.Patient;

import lombok.experimental.UtilityClass;

@UtilityClass // Makes all methods static
public class MedicalRecordMapper {

    //  Convert DTO to Entity
    public MedicalRecord toEntity(MedicalRecordRequestDTO dto, Patient patient, Doctor doctor) {
        if (dto == null) return null;

        return MedicalRecord.builder()
                .diagnosis(dto.getDiagnosis())
                .treatment(dto.getTreatment())
                .prescription(dto.getPrescription())
                .recordDate(dto.getRecordDate())
                .patient(patient)
                .doctor(doctor)
                .build();
    }

    //  Convert Entity to ResponseDTO
    public MedicalRecordResponseDTO toResponseDTO(MedicalRecord record) {
        if (record == null) return null;

        List<PrescriptionResponseDTO> prescriptions = null;
        if (record.getPrescriptions() != null) {
            prescriptions = record.getPrescriptions()
                    .stream()
                    .map(prescription -> {
                        return PrescriptionResponseDTO.builder()
                                .id(prescription.getId())
                                .medicineName(prescription.getMedicineName())
                                .dosage(prescription.getDosage())
                                .frequency(prescription.getFrequency())
                                .durationInDays(prescription.getDurationInDays())
                                .prescriptionDateTime(prescription.getPrescriptionDateTime())
                                .instructions(prescription.getInstructions())
                                .doctorid(prescription.getDoctor().getId())
                                .patientid(prescription.getPatient().getId())
                                .medicalRecordId(prescription.getMedicalRecord().getId())
                                .build();
                    })
                    .collect(Collectors.toList());
        }

        return MedicalRecordResponseDTO.builder()
                .id(record.getId())
                .diagnosis(record.getDiagnosis())
                .treatment(record.getTreatment())
                .prescription(record.getPrescription())
                .recordDate(record.getRecordDate())
                .patientId(record.getPatient().getId())
                .doctorId(record.getDoctor().getId())
                .prescriptions(prescriptions)
                .build();
    }

    //  Update Entity from DTO (for PUT request)
    public void updateEntityFromDTO(MedicalRecordRequestDTO dto, MedicalRecord record) {
        if (dto == null || record == null) return;

        record.setDiagnosis(dto.getDiagnosis());
        record.setTreatment(dto.getTreatment());
        record.setPrescription(dto.getPrescription());
        record.setRecordDate(dto.getRecordDate());
        // Note: Patient and Doctor update is not included here; handle separately if needed
    }
}
