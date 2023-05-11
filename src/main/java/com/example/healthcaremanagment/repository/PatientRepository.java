package com.example.healthcaremanagment.repository;

import com.example.healthcaremanagment.entity.Doctor;
import com.example.healthcaremanagment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {

}
