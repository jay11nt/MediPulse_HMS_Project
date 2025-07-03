package com.ho.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Find patient by email
    Optional<Patient> findByEmail(String email);	//used Optional for Null handling(Optional is best practice for safety in null scenarios.)

    // Find patient by contact number
    Optional<Patient> findByContactNumber(String contactNumber);

    // Check existence by email
    boolean existsByEmail(String email);

    // Check existence by contact number
    boolean existsByContactNumber(String contactNumber);
    
    // check by fullName 
    boolean existsByFullName(String fullName);
}





//==============in Repository always try to find data in unique method to avoid duplication data recieve========================





//======existByEmail logic============

//@PostMapping("/register")
//public ResponseEntity<?> registerPatient(@RequestBody @Valid PatientRequestDTO dto) {
//    if (patientRepository.existsByEmail(dto.getEmail())) {
//        return ResponseEntity.badRequest().body("Email already in use");
//    }
//====if email not alredy registered then here creates======
//    Patient patient = PatientMapper.toEntity(dto);
//    patientRepository.save(patient);
//    return ResponseEntity.ok("Registration successful");