package com.ho.dto.request;

import com.ho.entity.Doctor.Specialization;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequestDTO {
	
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

	    
	    @NotNull(message = "Specialization is required")
	    private Specialization specialization;

	    private Double consultationFee;
	    
	    @NotNull(message = "Qualification is required")
	    private String qualification;

	    @NotNull(message = "Experience is required")
	    private int experienceInYears;
	    
	    private Long adminId;

}
