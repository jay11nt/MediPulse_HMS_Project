package com.ho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Doctor;
import com.ho.entity.MedicalRecord;
import com.ho.entity.Patient;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    // Find all medical records for a specific patient
    List<MedicalRecord> findByPatient(Patient patient);

    // Find all medical records written by a specific doctor
    List<MedicalRecord> findByDoctor(Doctor doctor);

    // Optional: find recent records for a patient (if MedicalRecord has a 'createdDate')
    // List<MedicalRecord> findTop5ByPatientOrderByCreatedDateDesc(Patient patient);
    
}
