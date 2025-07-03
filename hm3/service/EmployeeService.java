package com.ho.service;

import java.util.List;

import com.ho.dto.request.EmployeeRequestDTO;
import com.ho.dto.response.EmployeeResponseDTO;

public interface EmployeeService {

	EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto);

    EmployeeResponseDTO getEmployeeById(Long id);

    List<EmployeeResponseDTO> getAllEmployees();

    void deleteEmployee(Long id);

    List<EmployeeResponseDTO> getEmployeesByFullName(String fullName);

    List<EmployeeResponseDTO> getEmployeesByRole(String role);
}
