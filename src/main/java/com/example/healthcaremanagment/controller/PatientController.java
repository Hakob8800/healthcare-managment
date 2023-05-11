package com.example.healthcaremanagment.controller;

import com.example.healthcaremanagment.entity.Doctor;
import com.example.healthcaremanagment.entity.Patient;
import com.example.healthcaremanagment.repository.DoctorRepository;
import com.example.healthcaremanagment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository pr;

    @GetMapping()
    public String allPatients(ModelMap modelMap) {
        List<Patient> patientList = pr.findAll();
        modelMap.addAttribute(patientList);
        return "allPatients";
    }

    @GetMapping("/add")
    public String addPatientGet() {
        return "addPatient";
    }


    @PostMapping("/add")
    public String addPatientPost(@ModelAttribute Patient patient) {
        pr.save(patient);
        return "redirect:/patients";
    }
    @GetMapping("/remove")
    public String removePatient(@RequestParam("id") int id) {
        pr.deleteById(id);
        return "redirect:/patients";
    }

}
