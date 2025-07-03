package com.ho.service;

import com.ho.dto.request.LoginRequestDTO;
import com.ho.dto.response.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO requestDTO);
}
