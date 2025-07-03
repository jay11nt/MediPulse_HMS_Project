# MediPulse_HMS_Project
The Developer's Team builds the system for the hospital’s internal use.

## 🏥 (HMS) Hospital Management System:-
A comprehensive Hospital Management System built using Java Spring Boot + Hibernate + MySQL with clean architecture and layered structure — designed for real-world hospital operations.
It helps manage Patients, Doctors, Appointments, Billing, Employees, Rooms, Prescriptions, and more.

## ✨ Features :

✅ Admin Panel

✅ Patient Management

✅ Doctor Management with Specialization & Fee

✅ Appointment Scheduling with SMS Notification (via Twilio)

✅ Room Allocation & Management

✅ Employee Management (Nurse, Receptionist, etc)

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

⚙️ Setup Instructions :

1️.  Clone this Repository-

    git clone https://github.com/your-username/hospital-management-system.git
    cd hospital-management-system

2️. Configure MySQL Database-

    # application.properties
    spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    spring.jpa.hibernate.ddl-auto=update

3️. Run the Application

4️. Access API Endpoints-

    http://localhost:8080/api/patients
    http://localhost:8080/api/doctors
    http://localhost:8080/api/appointments
    http://localhost:8080/api/invoices
    http://localhost:8080/api/rooms
    http://localhost:8080/api/employees
    http://localhost:8080/api/prescriptions

## 📑 API Endpoints Overview :

        Resource	    Endpoint
        Patients	    /api/patients
        Doctors	      /api/doctors
        Appointments    /api/appointments
        Rooms           /api/rooms
        Employees       /api/employees
        Prescriptions   /api/prescriptions
        Invoices        /api/invoices
        Admins	      /api/admins

## 🚦 Future Enhancements

    •	 JWT Authentication & Authorization
    •	 Swagger UI / OpenAPI Docs
    •	 Role-based Access Control (RBAC)
    •	 Patient Portal with Login
    •	 Angular/React Frontend Integration

### 🙏 Credits
Built with ❤️ by Jayant Ingle

