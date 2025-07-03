package com.ho.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"medicalRecord", "doctor", "patient"})
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Medicine name is required")
    private String medicineName;

    @NotBlank(message = "Dosage is required")
    private String dosage; // e.g., "500mg"

    @NotBlank(message = "Frequency is required")
    private String frequency; // e.g., "Twice a day"

    @NotNull(message = "Duration is required")
    private Integer durationInDays; // e.g., 7 days
    
    @NotNull(message = "date-time is always required for prescription")
    private LocalDateTime prescriptionDateTime;

    private String instructions; // Optional notes like "after food"

	    // =====Mappings =====
	
	    // Linked to the Medical Record
	    @ManyToOne
	    @JoinColumn(name = "medical_record_id")
	    private MedicalRecord medicalRecord;
	
	    // Prescribed by which doctor
	    @ManyToOne
	    @JoinColumn(name = "doctor_id")
	    private Doctor doctor;
	
	    // For which patient
	    @ManyToOne
	    @JoinColumn(name = "patient_id")
	    private Patient patient;
}
