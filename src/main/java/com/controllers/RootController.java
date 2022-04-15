package com.controllers;

import com.dto.MediaDTO;
import com.models.documents.Media;
import com.models.enums.MediaType;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RootController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/")
    public String getIndex(Model model){
        return "index";
    }
    @GetMapping("/users")
    public String getUsers(Model model){
        return "users";
    }
    @GetMapping("/employees")
    public String getEmployes(Model model){
        return "employees";
    }
    @GetMapping("/newLivre")
    public String getNewLivre(Model model){
        return "newLivre";
    }
    @GetMapping("/newMedia")
    public String getNewMedia(Model model){
        MediaDTO mediaDTO = new MediaDTO();
        model.addAttribute("MediaDTO",mediaDTO);
        return "newMedia";
    }
    @GetMapping("/createClient")
    public String getCreateClient(Model model){
        return "createClient";
    }
    @GetMapping("/emprunter")
    public String getEmprunter(Model model){
        return "emprunter";
    }
    @GetMapping("/retourner")
    public String getRetourner(Model model){
        return "retourner";
    }

    @PostMapping("/newMedia")
    public String mediaPost(@ModelAttribute MediaDTO mediaDTO) {
        employeeService.saveMedia(
                mediaDTO.getTitre(),
                mediaDTO.getAuteur(),
                mediaDTO.getEditeur(),
                Integer.parseInt(mediaDTO.getAnneeDePublication()),
                Integer.parseInt(mediaDTO.getNbExemplaires()),
                Integer.parseInt(mediaDTO.getTempsEmprunt()),
                mediaDTO.getDuree(),
                MediaType.valueOf(mediaDTO.getType()));
        return "employees";
    }
}
