package com.ho.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ho.dto.request.AdminRequestDTO;
import com.ho.dto.response.AdminResponseDTO;
import com.ho.entity.Admin;
import com.ho.exception.ResourceNotFoundException;
import com.ho.mapper.AdminMapper;
import com.ho.repository.AdminRepository;
import com.ho.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public AdminResponseDTO createAdmin(AdminRequestDTO dto) {
        Admin admin = AdminMapper.toEntity(dto);
        Admin saved = adminRepository.save(admin);
        return AdminMapper.toResponseDTO(saved);
    }

    @Override
    public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO dto) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", id));
        AdminMapper.updateEntityFromDTO(dto, admin);
        Admin updated = adminRepository.save(admin);
        return AdminMapper.toResponseDTO(updated);
    }

    @Override
    public AdminResponseDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", id));
        return AdminMapper.toResponseDTO(admin);
    }

    @Override
    public List<AdminResponseDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(AdminMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", id));
        adminRepository.delete(admin);
    }

    @Override
    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    @Override
    public AdminResponseDTO getAdminByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "username", username));
        return AdminMapper.toResponseDTO(admin);
    }
    
   
}


