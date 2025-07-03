package com.ho.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@EqualsAndHashCode(exclude = {"appointments", "medicalRecords", "patients"})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Full name is required")
    @Size(min = 4, max = 50, message = "Full name must be between 4 and 50 characters")
    private String fullName;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone must be between 10 and 15 digits")
    @Column(length = 15)
    private String contactNumber;

    public enum Specialization { GENERAL_PHYSICIAN,
        							CARDIOLOGIST,
        							NEUROLOGIST,
        							DERMATOLOGIST,
        							PEDIATRICIAN,
        							ORTHOPEDIC,
        							GYNECOLOGIST
    							}
    @NotNull(message = "Specialization is required")
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    
   
    private Double consultationFee;

    @NotNull(message = "Qualification is required")
    private String qualification;

    @NotNull(message = "Experience is required")
    private int experienceInYears;

	    // One doctor can have many appointments
	    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Appointment> appointments;

	    // One doctor can write many medical records
	    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<MedicalRecord> medicalRecords;
	    
	    @ManyToOne  
	    @JoinColumn(name = "admin_id")		//FK
	    private Admin admin;
	    
	    @ManyToMany							//new table
	    @JoinTable(
	        name = "assigned_doctor_patient",
	        joinColumns = @JoinColumn(name = "doctor_id"),
	        inverseJoinColumns = @JoinColumn(name = "patient_id"))
	    private List<Patient> patients;
}
