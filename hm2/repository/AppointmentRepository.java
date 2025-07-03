package com.ho.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Appointment;
import com.ho.entity.Doctor;
import com.ho.entity.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Get all appointments for a specific patient
    List<Appointment> findByPatient(Patient patient);

    // Get all appointments for a specific doctor
    List<Appointment> findByDoctor(Doctor doctor);

    // Find appointments scheduled at a specific time (Date+Time)
    List<Appointment> findByAppointmentDateTime(LocalDateTime appointmentdateTime);

    // Optional: Find by doctor and time (to check for clashes)
    //Check if a doctor is already booked at a certain time.
    boolean existsByDoctorAndAppointmentDateTime(Doctor doctor, LocalDateTime dateTime);      
    
    // Find all appointments by Date (BETTER)
    List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
