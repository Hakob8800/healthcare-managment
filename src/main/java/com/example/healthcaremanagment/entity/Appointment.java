package com.example.healthcaremanagment.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date dateTime;
}
