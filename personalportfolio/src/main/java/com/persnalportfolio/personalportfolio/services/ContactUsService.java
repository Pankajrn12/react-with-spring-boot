package com.persnalportfolio.personalportfolio.services;

import com.persnalportfolio.personalportfolio.entities.Admin;
import com.persnalportfolio.personalportfolio.entities.CareerForms;
import com.persnalportfolio.personalportfolio.entities.ContactUs;
import com.persnalportfolio.personalportfolio.entities.Registration;
import com.persnalportfolio.personalportfolio.models.AdminModel;
import com.persnalportfolio.personalportfolio.models.CareerFormsModels;
import com.persnalportfolio.personalportfolio.models.ContactUsModels;
import com.persnalportfolio.personalportfolio.models.RegistrationModel;
import com.persnalportfolio.personalportfolio.repositories.AdminRepository;
import com.persnalportfolio.personalportfolio.repositories.CareerRepository;
import com.persnalportfolio.personalportfolio.repositories.ContactUsRepository;
import com.persnalportfolio.personalportfolio.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactUsService {
    @Autowired
    ContactUsRepository contactUsRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String formMail;
    @Autowired
    CareerRepository careerRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    RegistrationRepository registrationRepository;

    @Transactional
    public ResponseEntity saveContact(ContactUsModels contactUsModels) {
        ContactUs contactUs = new ContactUs();
        contactUs.setEmail(contactUsModels.getEmail());
        contactUs.setMobileNumber(contactUsModels.getMobileNumber());
        contactUs.setId(contactUsModels.getId());
        contactUs.setMsg(contactUsModels.getMsg());
        contactUs.setName(contactUsModels.getName());

        contactUsRepository.save(contactUs);
        String msg = "Dear "+contactUsModels.getName()+"," +
                "\n" +
                "\n"+
                "This is to let you know that we have received your application." +
                "We appreciate your your interest in City Infotech Software pvt. ltd." +
                "and the contact Request submit successfully you can expect a phone call form our" +
                "Human Resources (HR) staff shortly." +
                "\n" +
                "\n"+
                "Thank you, again, for your interest in our company, We do appreciate the time you" +
                "invested in this application. \n" +
                "\n" +
                "\n"+
                "Sincerely \n" +
                "\n" +
                "HR Manager \n" +
                "City Infotech Software pvt. ltd. \n" +
                "MaharajGanj Uttar Pradesh India (273303)";
        String subject = "City Infotech software PVT. LTD. MaharajGanj";
        javaMailSender(formMail,contactUsModels.getEmail(),subject, msg);
        return new ResponseEntity<>("contact Details Submitted successfully", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity careerFormSubmitted(CareerFormsModels careerFormsModels) {
        CareerForms careerForms = new CareerForms();
        careerForms.setMobile(careerFormsModels.getMobile());
        careerForms.setLocation(careerFormsModels.getLocation());
        careerForms.setEmail(careerFormsModels.getEmail());
        careerForms.setName(careerFormsModels.getName());
        careerForms.setId(careerFormsModels.getId());
        careerForms.setResumePath(careerFormsModels.getResumePath());
        careerForms.setPosition(careerFormsModels.getPosition());

        careerRepository.save(careerForms);
        String msg = "Dear " + careerFormsModels.getName()+","+"\n"+
                "\n"+ "This is to let you know that we have received your application." +
                        " We appreciate your your interest in City Infotech Software pvt. ltd."+
                        "and the posion of "+careerFormsModels.getPosition()+" for which you applied."+
                        "If you are selected for and interview, you can expect a phone call form our " +
                        "Human Resources (HR) staff shortly. \n"+
                        "\n"+
                        "Thank you, again, for your interest in our company, We do appreciate the time you" +
                        " invested in this application. \n" +
                        "\n" +
                        "Sincerely \n" +
                        "\n" +
                        "HR Manager \n" +
                        "City Infotech Software pvt. ltd. \n" +
                        "MaharajGanj Uttar Pradesh India (273303)";
        String subject = "City Infotech software PVT. LTD. MaharajGanj";
        javaMailSender(formMail,careerFormsModels.getEmail(),subject, msg);

        return new ResponseEntity<>("Your Forms is successfully submitted", HttpStatus.OK);
    }

    private void javaMailSender(String formMail, String toEmail, String subject, String msg) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(formMail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msg);

        javaMailSender.send(simpleMailMessage);
    }

    public List<CareerFormsModels> getCareer() {
        List<CareerFormsModels> careerFormsModelsList = new ArrayList<>();
        List<CareerForms> careerFormsList = careerRepository.findAll();
        careerFormsList.stream().forEach(data->{
            CareerFormsModels models = new CareerFormsModels();
            models.setEmail(data.getEmail());
            models.setId(data.getId());
            models.setLocation(data.getLocation());
            models.setMobile(data.getMobile());
            models.setName(data.getName());
            models.setResumePath(data.getResumePath());

            careerFormsModelsList.add(models);
        });
        return careerFormsModelsList;
    }

    @Transactional
    public ResponseEntity saveAdmin(AdminModel adminModel) {
        Admin admin = new Admin();
        admin.setId(adminModel.getId());
        admin.setAdminId(adminModel.getAdminId());
        admin.setPassword(adminModel.getPassword());

        adminRepository.save(admin);
        return new ResponseEntity<>("Admin saved successfully", HttpStatus.OK);

    }

    public ResponseEntity validateUser(AdminModel adminModel) {
        Admin admin = adminRepository.findByAdminId(adminModel.getAdminId());
        if(admin==null){
            return new ResponseEntity<>("Admin id is incorrect.", HttpStatus.FORBIDDEN);
        } else {
            if (admin.getPassword().equalsIgnoreCase(adminModel.getPassword())){
                return new ResponseEntity<>("Successfully validated.", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Password is incorrect.", HttpStatus.FORBIDDEN);
            }
        }
    }

    public List<ContactUsModels> getContactUs() {
        List<ContactUsModels> contactUsModelsList = new ArrayList<>();
        List<ContactUs>  contactUsList = contactUsRepository.findAll();
        contactUsList.stream().forEach(data->{
            ContactUsModels models = new ContactUsModels();
            models.setEmail(data.getEmail());
            models.setId(data.getId());
            models.setName(data.getName());
            models.setMsg(data.getMsg());
            models.setMobileNumber(data.getMobileNumber());

            contactUsModelsList.add(models);
        });
        return contactUsModelsList;
    }

    @Transactional
    public ResponseEntity userRegistration(RegistrationModel registrationModel) {
        Registration registration = new Registration();
        registration.setAddress(registrationModel.getAddress());
        registration.setCollegeName(registrationModel.getCollegeName());
        registration.setId(registrationModel.getId());
        registration.setMobile(registrationModel.getMobile());
        registration.setEmail(registrationModel.getEmail());
        registration.setName(registrationModel.getName());
        registration.setUploadImage(registrationModel.getUploadImage());
        registration.setHighestQualification(registrationModel.getHighestQualification());
        registration.setGender(registrationModel.getGender());

        registrationRepository.save(registration);
        return new ResponseEntity<>("User Registration SuccessFully", HttpStatus.OK);
    }

    public List<RegistrationModel> getUserRegistrationDetails() {
        List<RegistrationModel> registrationModelList = new ArrayList<>();
        List<Registration> registrationList = registrationRepository.findAll();
        registrationList.stream().forEach(data->{
            RegistrationModel model = new RegistrationModel();
            model.setAddress(data.getAddress());
            model.setId(data.getId());
            model.setGender(data.getGender());
            model.setUploadImage(data.getUploadImage());
            model.setName(data.getName());
            model.setMobile(data.getMobile());
            model.setHighestQualification(data.getHighestQualification());
            model.setEmail(data.getEmail());
            model.setCollegeName(data.getCollegeName());

            registrationModelList.add(model);
        });
        return registrationModelList;
    }
}
