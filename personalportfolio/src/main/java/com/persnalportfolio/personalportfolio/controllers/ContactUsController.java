package com.persnalportfolio.personalportfolio.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.persnalportfolio.personalportfolio.entities.CareerForms;
import com.persnalportfolio.personalportfolio.models.AdminModel;
import com.persnalportfolio.personalportfolio.models.CareerFormsModels;
import com.persnalportfolio.personalportfolio.models.ContactUsModels;
import com.persnalportfolio.personalportfolio.models.RegistrationModel;
import com.persnalportfolio.personalportfolio.services.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/ContactUs")
public class ContactUsController {
    @Autowired
    ContactUsService contactUsService;

    @PostMapping("/saveContact")
    public ResponseEntity saveContact(@RequestBody ContactUsModels contactUsModels){
        return contactUsService.saveContact(contactUsModels);
    }

    @PostMapping("/careerFormSubmitted")
    public ResponseEntity careerFormSubmitted(@RequestBody CareerFormsModels careerFormsModels){
        return contactUsService.careerFormSubmitted(careerFormsModels);
    }

    @GetMapping("getCareer")
    public List<CareerFormsModels> getCareer(){
        return contactUsService.getCareer();
    }

    @GetMapping("/getContactUs")
    public List<ContactUsModels> getContactUs(){
        return contactUsService.getContactUs();
    }
    @PostMapping("/saveAdmin")
    public ResponseEntity saveAdmin(@RequestBody AdminModel adminModel){
        return contactUsService.saveAdmin(adminModel);
    }

    @PostMapping("/validateUser")
    public ResponseEntity validateUser(@RequestBody AdminModel adminModel){
        return contactUsService.validateUser(adminModel);
    }

    @PostMapping("/userRegistration")
    public ResponseEntity userRegistration(@RequestBody RegistrationModel registrationModel){
        return contactUsService.userRegistration(registrationModel);
    }

    @GetMapping("/getUserRegistrationDetails")
    public List<RegistrationModel> getUserRegistrationDetails(){
        return contactUsService.getUserRegistrationDetails();
    }

}
