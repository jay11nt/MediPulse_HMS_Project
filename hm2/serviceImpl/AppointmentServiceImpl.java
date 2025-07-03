package com.ho.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ho.dto.request.AppointmentRequestDTO;
import com.ho.dto.response.AppointmentResponseDTO;
import com.ho.entity.Appointment;
import com.ho.entity.Doctor;
import com.ho.entity.Patient;
import com.ho.exception.ResourceNotFoundException;
import com.ho.mapper.AppointmentMapper;
import com.ho.repository.AppointmentRepository;
import com.ho.repository.DoctorRepository;
import com.ho.repository.PatientRepository;
import com.ho.service.AppointmentService;
import com.ho.sms.SmsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SmsService smsService;
    
    
    private final String hospitalName = "Lifeline Multispeciality Hospital";
    private final String hospitalAddress = "516 Garware Street, Nagpur, MH";

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", dto.getDoctorId()));
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dto.getPatientId()));

        Appointment appointment = AppointmentMapper.toEntity(dto, patient, doctor);
        Appointment saved = appointmentRepository.save(appointment);
        
        //====== ✅ Format date/time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        String formattedDateTime = saved.getAppointmentDateTime().format(formatter);

        //===== ✅ Send SMS to Patient (India format: +91XXXXXXXXXX)=====
        String PatientMessage = "Dear " + patient.getFullName() + 
        		", your appointment with Dr. " + doctor.getFullName() +
        		" has been scheduled on " + formattedDateTime + 				//appointment.getAppointmentDateTime().toString();
        		" at " + hospitalName + ", " + hospitalAddress + ".";

        smsService.sendSms("+91" + patient.getContactNumber(), PatientMessage);

        //====== ✅ Send SMS to Doctor ============
        String doctorMessage = "Dr. " + doctor.getFullName() + 
        		", you have a new appointment with patient " + patient.getFullName() + 
        		" on " + formattedDateTime +										//appointment.getAppointmentDateTime().toString();
        		" at " + hospitalName + ", " + hospitalAddress + ".";

        smsService.sendSms("+91" + doctor.getContactNumber(), doctorMessage);
        
        return AppointmentMapper.toResponseDTO(saved);
    }

    @Override
    public AppointmentResponseDTO updatedAppointment(Long id, AppointmentRequestDTO dto) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));

        existing.setAppointmentDateTime(dto.getAppointmentDateTime());
        existing.setStatus(dto.getStatus());
        existing.setRemarks(dto.getRemarks());

        if (dto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", dto.getDoctorId()));
            existing.setDoctor(doctor);
        }

        if (dto.getPatientId() != null) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dto.getPatientId()));
            existing.setPatient(patient);
        }

        Appointment updated = appointmentRepository.save(existing);
        return AppointmentMapper.toResponseDTO(updated);
    }


    @Override
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO dto) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));

        existing.setAppointmentDateTime(dto.getAppointmentDateTime());
        existing.setStatus(dto.getStatus());
        existing.setRemarks(dto.getRemarks());

        if (dto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", dto.getDoctorId()));
            existing.setDoctor(doctor);
        }

        if (dto.getPatientId() != null) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dto.getPatientId()));
            existing.setPatient(patient);
        }

        Appointment updated = appointmentRepository.save(existing);
        return AppointmentMapper.toResponseDTO(updated);
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
        return AppointmentMapper.toResponseDTO(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(AppointmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
        return appointmentRepository.findByPatient(patient).stream()
                .map(AppointmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByDoctorId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
        return appointmentRepository.findByDoctor(doctor).stream()
                .map(AppointmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return appointmentRepository.findAll().stream()
                .filter(appointment -> {
                    LocalDateTime appointmentDateTime = appointment.getAppointmentDateTime();
                    return (appointmentDateTime.isEqual(startOfDay) || appointmentDateTime.isAfter(startOfDay)) &&
                            (appointmentDateTime.isBefore(endOfDay) || appointmentDateTime.isEqual(endOfDay));
                })
                .map(AppointmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
// ====To Doctor:  (sms sent after appointment created)=======
//  Dr. Kavita Singh, you have a new appointment with patient Rahul Sharma 
//	on 07-06-2025 04:30 PM at Lifeline Multispeciality Hospital, 123 Health Street, Pune, MH.

//====same like this to patient=======
