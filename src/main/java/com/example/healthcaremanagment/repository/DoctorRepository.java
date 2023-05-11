package com.example.healthcaremanagment.repository;

import com.example.healthcaremanagment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

}
