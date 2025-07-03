package com.ho.dto.response;

import com.ho.entity.Doctor.Specialization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDTO {

    private Long id;

    private String fullName;

    private String email;

    private String contactNumber;

    private Specialization specialization;
    
    private Double consultationFee;

    private String qualification;

    private int experienceInYears;
    
    private Long adminId;
}
