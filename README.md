# MediPulse_HMS_Project
The system is built by Developers Team for the hospital’s own internal use.

## 🏥 (HMS) Hospital Management System :-
A comprehensive Hospital Management System built using Java Spring Boot + Hibernate + MySQL with clean architecture and layered structure — designed for real-world hospital operations.
It helps manage Patients, Doctors, Appointments, Billing, Employees, Rooms, Prescriptions, and more.

## ✨ Features :

✅ Admin Panel

✅ Patient Management

✅ Doctor Management with Specialization & Fee

✅ Appointment Scheduling with SMS Notification (via Twilio)

✅ Room Allocation & Management

✅ Employee Management (Nurse, Receptionist, Pharmacist etc)

✅ Medical Records & History

✅ Prescription Generation

✅ Invoice & Billing System

✅ Search Doctors by Specialization

✅ Role-based Data Flow

✅ Exception Handling & Validation

✅ DTOs for Clean API Layer

✅ SMS Notification Support

✅ MySQL Integration

✅ REST API for full CRUD


## 🚀 Tech Stack :

•	Backend: Java 17, Spring Boot, Spring Data JPA, Hibernate

•	Database: MySQL

•	SMS Service: Twilio

•	Validation: Jakarta Validation

•	API Testing: Postman

•	Build Tool: Maven

•	Version Control: Git, GitHub

## 🗂️ Project Structure :

    hospital-management-system/
    ├── src/main/java/com/ho
    │   ├── controller/        # REST Controllers
    │   ├── service/           # Service Interfaces
    │   ├── serviceImpl/       # Service Implementations
    │   ├── repository/        # JPA Repositories
    │   ├── entity/            # JPA Entities (Doctor, Patient, Room, Appointment, etc)
    │   ├── dto/               # DTOs (Request & Response)
    │   ├── sms/               # SMS Service (Twilio)
    │   ├── exception/         # Global Exception Handling
    │   └── HospitalManagementApplication.java
    ├── resources/
    │   ├── application.properties
    │   └── data.sql (optional)
    ├── pom.xml
    └── README.md


