package com.ho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Doctor;
import com.ho.entity.MedicalRecord;
import com.ho.entity.Patient;
import com.ho.entity.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    // Find all prescriptions for a specific patient
    List<Prescription> findByPatient(Patient patient);

    // Find all prescriptions prescribed by a specific doctor
    List<Prescription> findByDoctor(Doctor doctor);

    // Find all prescriptions linked to a specific medical record
    List<Prescription> findByMedicalRecord(MedicalRecord medicalRecord);

    // Optional: Search by medicine name
    List<Prescription> findByMedicineNameContainingIgnoreCase(String medicineName);
}
