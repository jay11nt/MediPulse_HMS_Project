package com.ho.service;

import java.util.List;

import com.ho.dto.request.AdminRequestDTO;
import com.ho.dto.response.AdminResponseDTO;

public interface AdminService {

    AdminResponseDTO createAdmin(AdminRequestDTO dto);

    AdminResponseDTO updateAdmin(Long id, AdminRequestDTO dto);

    AdminResponseDTO getAdminById(Long id);

    List<AdminResponseDTO> getAllAdmins();

    void deleteAdmin(Long id);
//-----------------CRUD ended---------------
    
    
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    AdminResponseDTO getAdminByUsername(String username);
}
