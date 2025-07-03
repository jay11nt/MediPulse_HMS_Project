package com.ho.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ho.dto.request.EmployeeRequestDTO;
import com.ho.dto.response.EmployeeResponseDTO;
import com.ho.entity.Admin;
import com.ho.entity.Employee;
import com.ho.exception.InvalidRequestException;
import com.ho.exception.ResourceNotFoundException;
import com.ho.mapper.EmployeeMapper;
import com.ho.repository.AdminRepository;
import com.ho.repository.EmployeeRepository;
import com.ho.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

        // Admin (FK)    -- Converts DTO → Entity → saves → ResponseDTO
        if (dto.getAdminId() == null) {
            throw new InvalidRequestException("Admin ID is required");
        }

        Admin admin = adminRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", dto.getAdminId()));

        // Map DTO -> Entity
        Employee employee = EmployeeMapper.toEntity(dto);
        employee.setAdmin(admin);

        // Save to DB
        Employee saved = employeeRepository.save(employee);

        // Return DTO response
        return EmployeeMapper.toResponseDTO(saved);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {

        // Load existing employee
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        // Update fields from DTO
        EmployeeMapper.updateEntityFromDTO(dto, existing);

        // Optional: update Admin
        if (dto.getAdminId() != null) {
            Admin admin = adminRepository.findById(dto.getAdminId())
                    .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", dto.getAdminId()));
            existing.setAdmin(admin);
        }

        Employee updated = employeeRepository.save(existing);

        return EmployeeMapper.toResponseDTO(updated);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        return EmployeeMapper.toResponseDTO(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeesByFullName(String fullName) {

        List<Employee> employees = employeeRepository.findByFullName(fullName);
        		if (employees.isEmpty()) {
        	        throw new ResourceNotFoundException("Employee", "fullName", fullName);
        	    }

        return employees.stream()
                .map(EmployeeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeesByRole(String role) {

        List<Employee> employees = employeeRepository.findByRole(role);
        if (employees.isEmpty()) 
        {
            throw new ResourceNotFoundException("Employee", "role", role);
        }

        return employees.stream()
                .map(EmployeeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
