package com.ho.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Find admin by username (this is for Authentication)  
    Optional<Admin> findByUsername(String username);

    // Check if an admin exists with the given username
    boolean existsByUsername(String username);

    // Find admin by email (optional but useful)
    Optional<Admin> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);
    
    //check by fullName
    boolean existsByFullName(String fullName);
}


//============ using (Optional)keyword to avoid Null safety  ====Using Optional<Admin>findByUsername  to find admin 
// what its actually done here is(modern way always use this way)-

//	Optional<Admin> optionalAdmin = adminRepository.findByUsername("admin123");
//	Admin admin = optionalAdmin.orElseThrow(() -> new RuntimeException("Admin not found"));
