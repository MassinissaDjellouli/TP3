package com.controllers;

import com.dto.LivreDTO;
import com.dto.MediaDTO;
import com.models.documents.Livre;
import com.models.documents.Media;
import com.models.enums.Genres;
import com.models.enums.MediaType;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
        LivreDTO livreDTO = new LivreDTO();
        model.addAttribute("LivreDTO",livreDTO);
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

    @PostMapping("/createLivre")
    public String livrePost(@Valid
                                @ModelAttribute LivreDTO livreDto,
                            BindingResult errors){
        if (errors.hasErrors()) {
            return "redirect:/formError/newLivre";
        }
        employeeService.saveLivre(
                livreDto.getTitre(),
                livreDto.getAuteur(),
                livreDto.getEditeur(),
                Integer.parseInt(livreDto.getAnneeDePublication()),
                Integer.parseInt(livreDto.getNbExemplaires()),
                Integer.parseInt(livreDto.getTempsEmprunt()),
                Integer.parseInt(livreDto.getNbPages()),
                Genres.valueOf(livreDto.getGenre()));
        return "employees";
    }
    @PostMapping("/createMedia")
    public String mediaPost(@Valid
                            @ModelAttribute MediaDTO mediaDTO,
                            BindingResult errors) {
        if (errors.hasErrors()) {
            return "redirect:/formError/newMedia";
        }
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
    @GetMapping("/formError/{form}")
    public String getFormError(Model model, @PathVariable String form){
        model.addAttribute("form",form);
        return "formError";
    }
}
