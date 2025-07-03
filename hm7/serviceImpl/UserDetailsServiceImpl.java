//package com.ho.serviceImpl;
//
//import com.ho.entity.Admin;
//import com.ho.repository.AdminRepository;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service							//1.-----this needed when we dont want to use Admin User from .properties file this will use User(Admin) from admin DB table
//@RequiredArgsConstructor			//2. also pls remove this annotation from main securityconfig file
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final AdminRepository adminRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Admin admin = adminRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + username));
//
//        return new org.springframework.security.core.userdetails.User(
//                admin.getUsername(),
//                admin.getPassword(), // This should be BCrypt encoded
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
//        );
//    }
//}
