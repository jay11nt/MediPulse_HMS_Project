package com.ho.mapper;

import com.ho.dto.request.AdminRequestDTO;
import com.ho.dto.response.AdminResponseDTO;
import com.ho.entity.Admin;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminMapper {

    public Admin toEntity(AdminRequestDTO dto) {
        if (dto == null) return null;

        return Admin.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .fullName(dto.getFullName())
                .contactNumber(dto.getContactNumber())
                .build();
    }

    public AdminResponseDTO toResponseDTO(Admin admin) {
        if (admin == null) return null;

        return AdminResponseDTO.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .email(admin.getEmail())
                .fullName(admin.getFullName())
                .contactNumber(admin.getContactNumber())
                .build();
    }

    public void updateEntityFromDTO(AdminRequestDTO dto, Admin admin) {
        if (dto == null || admin == null) return;

        admin.setUsername(dto.getUsername());
        admin.setPassword(dto.getPassword());
        admin.setEmail(dto.getEmail());
        admin.setFullName(dto.getFullName());
        admin.setContactNumber(dto.getContactNumber());
    }
}
