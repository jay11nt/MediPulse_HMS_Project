package com.ho.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@EqualsAndHashCode(exclude = "patients")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Room number is required")
    @Column(unique = true)
    private String roomNumber;

    public enum AllotedRoom {OPD, GENERAL, PRIVATE, ICU}
    @NotNull(message = "Room type is required")  
    @Enumerated(EnumType.STRING)
    private AllotedRoom roomType;

    
    private Boolean isAvailable;

	    // 🔗 One room can have multiple patients (e.g., in a general ward)
	    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER , cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonManagedReference
	    private List <Patient> patients;
	
	    // Optional future use:
	    // You can also add assigned Nurse or Doctor fields here if needed.
}
