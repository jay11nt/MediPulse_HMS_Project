package com.ho.entity;


import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@EqualsAndHashCode(exclude = {"appointments", "medicalRecords", "nurses", "doctors","room"})

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 15, message = "First name must be between 3 to 15 letters")
    private String firstName;

    @NotNull(message = "enter your fullname")
    @Size(min = 6, max = 30, message = "fullname must be between 6 to 30 letters")
    private String fullName;

    @NotNull(message ="Password cannot be null")
    private String password;

    public enum Gender { MALE, FEMALE, OTHER }
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @NotNull(message = "Age cannot be null")
    @Range(min = 2, max = 120, message = "Age must be between 2 and 120 years")
    private int age;

    @NotNull(message = "Address cannot be null")
    @Size(min = 10, max = 200, message = "Enter full address")
    private String address;

    @NotNull(message = "Phone number must not be null")
    @Pattern(regexp = "^\\+?[1-9]\\d{9,14}$", message = "Invalid phone number format")
    private String contactNumber;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    		//Mappings
		    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
		    private List<Appointment> appointments;
		
		    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
		    private List<MedicalRecord> medicalRecords;
		
		    //If you update a Patient, changes to associated Nurse links will also be reflected.
		    @ManyToMany(mappedBy = "patient", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
		    private List<Nurse> nurses;
		    
		    @ManyToMany(mappedBy = "patients")
		    private List<Doctor> doctors;

		    @ManyToOne
		    @JoinColumn(name = "room_id")
		    @JsonBackReference
		    private Room room;
}
