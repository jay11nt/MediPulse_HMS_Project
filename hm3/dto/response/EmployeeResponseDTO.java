package com.ho.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponseDTO {

	private Long id;
    private String fullName;
    private String role;
    private String email;
    private String contactNumber;
    private String address;
    private Double salary;
    private String joiningDate;

    private Long adminId;    // for reference
    
    private Long nurseid;
}
