package com.ho.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NurseResponseDTO {

    private Long id;

    private String fullName;

    private String email;

    private String contactNumber;

    private String qualification;

    // Optionally show IDs of assigned patients
    private List<Long> patientIds;
}
