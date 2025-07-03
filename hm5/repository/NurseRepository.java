package com.ho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {

    // Find nurses by department if such a field exists
    List<Nurse> findByFullName(String FullName);

    // Check if a nurse exists by contact number
    boolean existsByContactNumber(String contactNumber);

    // Optional: Find nurses assigned to a specific patient (if ManyToMany from Nurse side has patients)
    // @Query("SELECT n FROM Nurse n JOIN n.patients p WHERE p.id = :patientId")
    // List<Nurse> findByPatientId(Long patientId);
}
