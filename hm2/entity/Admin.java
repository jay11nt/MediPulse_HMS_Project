package com.ho.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@EqualsAndHashCode(exclude = {"doctors", "employees"})
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    @Column(name = "user_name",unique = true)
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Full name is required")
    @Size(min = 4, max = 50, message = "Full name must be between 4 and 50 characters")
    private String fullName;

    @NotNull(message = "Contact number is required")
    @Column(length = 10)
    private String contactNumber;

	    // Optional: Admin creates doctors
	    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade =CascadeType.ALL, orphanRemoval = true)  
	    private List<Doctor> doctors;

	    // Optional: Admin creates employees by Profession(nurse, receptionist, helper etc.) 
	    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Employee> employees;
	    
	    
}
