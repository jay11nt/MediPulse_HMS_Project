package com.ho.service;

import java.util.List;

import com.ho.dto.request.PatientRequestDTO;
import com.ho.dto.response.PatientResponseDTO;

public interface PatientService {

    PatientResponseDTO createPatient(PatientRequestDTO dto);  //create

    PatientResponseDTO updatePatient(Long id, PatientRequestDTO dto);	//update

    PatientResponseDTO getPatientById(Long id);		//Read

    List<PatientResponseDTO> getAllPatients();		//ReadAll

    void deletePatient(Long id);		//Delete  (if u dont want to return. like any code-404 or deleted data use void method only )


	List<String> getAllNurseNamesByPatientId(Long id);
}
