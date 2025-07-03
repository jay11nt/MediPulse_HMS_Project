package com.ho.dto.request;

import org.hibernate.validator.constraints.Range;

import com.ho.entity.Patient.Gender;

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
public class PatientRequestDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 15, message = "First name must be between 3 to 15 letters")
    private String firstName;

    @NotNull(message = "Enter your full name")
    @Size(min = 6, max = 30, message = "Full name must be between 6 to 30 letters")
    private String fullName;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @NotNull(message = "Age cannot be null")
    //@Min(value = 2, message = "Age must be at least 2 years")
   // @Max(value = 120, message = "Age cannot be more than 120 years")
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
    
    
    private Long roomId;
    
    //optional if u want seperate view roomnumber
//    @NotNull(message = "Room number is required")
//    @Column(unique = true)
//    private String roomNumber;
}
