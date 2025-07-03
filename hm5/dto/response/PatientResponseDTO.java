package com.ho.dto.response;

import com.ho.entity.Patient.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDTO {

    private Long id;

    private String firstName;

    private String fullName;

    private Gender gender;

    private int age;

    private String address;

    private String contactNumber;

    private String email;
    
    private Long roomId;    
    
   // private String roomNumber;

    // Optionally include appointment counts or brief summaries if needed
    // private int totalAppointments;
    // private int medicalRecordCount;
}
