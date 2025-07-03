package com.ho.mapper;

import com.ho.dto.request.EmployeeRequestDTO;
import com.ho.dto.response.EmployeeResponseDTO;
import com.ho.entity.Employee;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeMapper {

	 public Employee toEntity(EmployeeRequestDTO dto) {
	        if (dto == null) return null;

	        return Employee.builder()
	                .fullName(dto.getFullName())
	                .role(dto.getRole())
	                .email(dto.getEmail())
	                .contactNumber(dto.getContactNumber())
	                .address(dto.getAddress())
	                .salary(dto.getSalary())
	                .joiningDate(dto.getJoiningDate())
	                .build();
	    }

	    public EmployeeResponseDTO toResponseDTO(Employee employee) {
	        if (employee == null) return null;

	        return EmployeeResponseDTO.builder()
	                .id(employee.getId())
	                .fullName(employee.getFullName())
	                .role(employee.getRole())
	                .email(employee.getEmail())
	                .contactNumber(employee.getContactNumber())
	                .address(employee.getAddress())
	                .salary(employee.getSalary())
	                .joiningDate(employee.getJoiningDate())
	                .adminId(employee.getAdmin() != null ? employee.getAdmin().getId() : null)
	                .build();
	    }

	    public void updateEntityFromDTO(EmployeeRequestDTO dto, Employee employee) {
	        if (dto == null || employee == null) return;

	        employee.setFullName(dto.getFullName());
	        employee.setRole(dto.getRole());
	        employee.setEmail(dto.getEmail());
	        employee.setContactNumber(dto.getContactNumber());
	        employee.setAddress(dto.getAddress());
	        employee.setSalary(dto.getSalary());
	        employee.setJoiningDate(dto.getJoiningDate());
	    }
}
