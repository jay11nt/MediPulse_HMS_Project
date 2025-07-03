package com.ho.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequestDTO {
	
	@NotNull(message = "Full name is required")
    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
    private String fullName;

    @NotNull(message = "Role is required")
    @Size(min = 2, max = 30, message = "Role must be between 2 and 30 characters")
    private String role;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number is required")
    private String contactNumber;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Salary is required")
    private Double salary;

    @NotNull(message = "Joining date is required")
    private String joiningDate;

    // Admin ID (optional)
    private Long adminId;
    
    //nurse id
    private Long nurseid;
}


