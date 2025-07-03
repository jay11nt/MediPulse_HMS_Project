package com.ho.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ho.dto.request.LoginRequestDTO;
import com.ho.dto.response.LoginResponseDTO;
import com.ho.entity.Admin;
import com.ho.exception.InvalidRequestException;
import com.ho.repository.AdminRepository;
import com.ho.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) 
    {
        Admin admin = adminRepository.findByUsername(requestDTO.getUsername())
                .orElseThrow(() -> new InvalidRequestException("Username not found"));

        if (!admin.getPassword().equals(requestDTO.getPassword())) {
            throw new InvalidRequestException("Incorrect password");
        }

        return new LoginResponseDTO("Login successful", admin.getUsername());
    }
}
