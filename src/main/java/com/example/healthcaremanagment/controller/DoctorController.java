package com.example.healthcaremanagment.controller;

import com.example.healthcaremanagment.entity.Doctor;
import com.example.healthcaremanagment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository dr;
    @Value("${health_management.picture.upload}")
    private String uploadFolder;

    @GetMapping()
    public String allDoctors(ModelMap modelMap) {
        List<Doctor> doctorList = dr.findAll();
        modelMap.addAttribute(doctorList);
        return "allDoctors";
    }

    @GetMapping("/add")
    public String addDoctorGet() {
        return "addDoctor";
    }

    @PostMapping("/add")
    public String addDoctorPost(@ModelAttribute Doctor doctor, @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty() && multipartFile != null) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadFolder + fileName);
            multipartFile.transferTo(file);
            doctor.setProfilePic(fileName);
        }
        dr.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/remove")
    public String removeDoctor(@RequestParam("id") int id) {
        dr.deleteById(id);
        return "redirect:/doctors";
    }

    @GetMapping("/{id}")
    public String singleDoctorPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Doctor> byId = dr.findById(id);
        if (byId.isPresent()) {
            Doctor doctor = byId.get();
            modelMap.addAttribute("doctor", doctor);
            return "singleDoctor";
        } else {
            return "redirect:/doctors";
        }

    }
}
