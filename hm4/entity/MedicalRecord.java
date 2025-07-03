package com.ho.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@EqualsAndHashCode(exclude = {"patient", "doctor", "prescriptions"})
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

	    // Mappingss
    		//-----------parent-------------
    	@OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    	private List<Prescription> prescriptions;
    
    
	    @ManyToOne
	    @JoinColumn(name = "patient_id", nullable = false )
	    private Patient patient;
	
	    
	    @ManyToOne
	    @JoinColumn(name = "doctor_id", nullable = false)
	    private Doctor doctor;
	    
	   
}
