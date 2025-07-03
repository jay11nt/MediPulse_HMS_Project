package com.ho.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"patient", "doctor"})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Appointment date/time is required")
    @Future(message = "Appointment must be scheduled for a future date/time")
    private LocalDateTime appointmentDateTime;

    //@NotNull(message = "Department is required")
   // private String department;

    public enum AppointmentStatus { SCHEDULED, COMPLETED, CANCELLED }
    @NotNull(message = "Status is required")  
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    //next patient visit etc.
    private String remarks;

    		//mappings
		    @ManyToOne
		    @JoinColumn(name = "patient_id", nullable = false)
		    private Patient patient;

    
		    @ManyToOne
		    @JoinColumn(name = "doctor_id", nullable = false)
		    private Doctor doctor;

		    // Optional: If appointment is created by admin (e.g., via phone call or admin dashboard)
		    //    @ManyToOne
		    //    @JoinColumn(name = "created_by_admin_id")
		    //    private Admin createdByAdmin;
}
