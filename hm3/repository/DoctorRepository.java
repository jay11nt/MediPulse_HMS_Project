package com.ho.repository;						//=========here only fetch/find data from DB & stores in objects==============

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Doctor;
import com.ho.entity.Doctor.Specialization;

@Repository					
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Find doctor by email
    Optional<Doctor> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Find doctor by contact number
    Optional<Doctor> findByContactNumber(String contactNumber);

    // Check if contact number exists
    boolean existsByContactNumber(String contactNumber);

    // Find doctors by specialization
    List<Doctor> findBySpecialization(Specialization specialization);
}
