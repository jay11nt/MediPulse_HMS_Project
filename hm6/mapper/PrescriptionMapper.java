package com.ho.mapper;

import com.ho.dto.request.PrescriptionRequestDTO;
import com.ho.dto.response.PrescriptionResponseDTO;
import com.ho.entity.Doctor;
import com.ho.entity.MedicalRecord;
import com.ho.entity.Patient;
import com.ho.entity.Prescription;

public class PrescriptionMapper {

    // ✅ Convert DTO to Entity
    public static Prescription toEntity(PrescriptionRequestDTO dto, Doctor doctor, Patient patient, MedicalRecord medicalRecord) 
    {	 if (dto == null) return null;
        return Prescription.builder()
                .medicineName(dto.getMedicineName())
                .dosage(dto.getDosage())
                .frequency(dto.getFrequency())
                .durationInDays(dto.getDurationInDays())
                .prescriptionDateTime(dto.getPrescriptionDateTime())
                .instructions(dto.getInstructions())
                .doctor(doctor)
                .patient(patient)
                .medicalRecord(medicalRecord)
                .build();
    }

    // ✅ Convert Entity to ResponseDTO
    public static PrescriptionResponseDTO toResponseDTO(Prescription prescription) {
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
    }

		public static void updateEntityFromDTO(PrescriptionRequestDTO dto, Prescription existing, Doctor doctor,
			            Patient patient, MedicalRecord medicalRecord) 
		{
			if (dto == null || existing == null) return;
			
			existing.setMedicineName(dto.getMedicineName());
			existing.setDosage(dto.getDosage());
			existing.setFrequency(dto.getFrequency());
			existing.setDurationInDays(dto.getDurationInDays());
			existing.setPrescriptionDateTime(dto.getPrescriptionDateTime());
			existing.setInstructions(dto.getInstructions());
			existing.setDoctor(doctor);
			existing.setPatient(patient);
			existing.setMedicalRecord(medicalRecord);
			}
}
