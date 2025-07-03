package com.ho.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employee by email
    Optional<Employee> findByEmail(String email);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find employee by contact number
    Optional<Employee> findByContactNumber(String contactNumber);

    // Check if contact number exists
    boolean existsByContactNumber(String contactNumber);

    // Optional: Find by role (e.g., receptionist, admin staff, etc.) if role exists as a field
    List<Employee>findByRole(String role);
    
    //find by fullname
    List<Employee> findByFullName(String fullName);
}
