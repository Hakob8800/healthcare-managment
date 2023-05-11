package com.example.healthcaremanagment.controller;

import com.example.healthcaremanagment.entity.Appointment;
import com.example.healthcaremanagment.entity.Doctor;
import com.example.healthcaremanagment.entity.Patient;
import com.example.healthcaremanagment.repository.AppointmentRepository;
import com.example.healthcaremanagment.repository.DoctorRepository;
import com.example.healthcaremanagment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentRepository ar;
    @Autowired
    private DoctorRepository dr;
    @Autowired
    private PatientRepository pr;

    @GetMapping()
    public String allAppointments(ModelMap modelMap) {
        List<Appointment> appointmentList = ar.findAll();
        modelMap.addAttribute(appointmentList);
        return "allAppointments";
    }

    @GetMapping("/add")
    public String addAppointmentGet(ModelMap modelMap) {
        List<Doctor> doctorList = dr.findAll();
        List<Patient> patientList = pr.findAll();
        modelMap.addAttribute(doctorList);
        modelMap.addAttribute(patientList);
        return "addAppointment";
    }


    @PostMapping("/add")
    public String addAppointmentPost(@ModelAttribute Appointment appo) {
        ar.save(appo);
        return "redirect:/appointments";
    }

    @GetMapping("/remove")
    public String removeAppointment(@RequestParam("id") int id) {
        ar.deleteById(id);
        return "redirect:/appointments";
    }

}
