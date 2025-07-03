package com.ho.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
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
@EqualsAndHashCode(exclude = "patient")
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "fullName cannot be null")
    @Size(min = 6, max = 30, message = "fullName must be between 6 to 30 characters")
    private String fullName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Column(length = 15)
    private String contactNumber;

    @NotNull(message = "qualification is must")
    private String qualification;

    		//mapping
    		@ManyToMany
		    @JoinTable(
			        	name = "nurse_patient",
			        	joinColumns = @JoinColumn(name = "nurse_id"),
			        	inverseJoinColumns = @JoinColumn(name = "patient_id")
		    		)
    		
    private List<Patient> patient;
}
