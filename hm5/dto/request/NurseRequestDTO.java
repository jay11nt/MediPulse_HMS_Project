package com.ho.dto.request;

import java.util.List;

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
public class NurseRequestDTO {
	 @NotNull(message = "Full name cannot be null")
	    @Size(min = 6, max = 30, message = "Full name must be between 6 to 30 characters")
	    private String fullName;

	    @NotNull(message = "Email cannot be null")
	    @Email(message = "Invalid email format")
	    private String email;

	    @NotNull(message = "Contact number cannot be null")
	    @Pattern(regexp = "^[0-9]{10,15}$", message = "Contact number must be between 10 and 15 digits")
	    private String contactNumber;

	    @NotNull(message = "Qualification is required")
	    private String qualification;

	    // Accept list of patient IDs to assign during nurse creation
	    private List<Long> patientIds;
	}

