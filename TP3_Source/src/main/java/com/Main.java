package com;

import com.dto.*;
import com.models.enums.Genres;
import com.models.enums.MediaType;
import com.models.users.Client;
import com.service.ClientService;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    ClientService clientService;
    @Autowired
    EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }


    @Override
    public void run(String... args){
        //Todo Enregistrement d'un client:
        int clId = clientService.saveClient("name","adress","phone");
        //Todo Ajout d'un livre:
        int liId = employeeService.saveLivre("titre", "auteur numero 2", "editeur", 2000, 3, 30, 100, Genres.roman);
        int li2Id = employeeService.saveLivre("titre numero 2", "auteur", "editeur", 2000, 3, 30, 100, Genres.roman);
        //Todo Ajout d'un media:
        int meId = employeeService.saveMedia("titre","auteur","editeur",2000,3,30,"40min", MediaType.dvd);
        //Todo Recherche de titre:
            List<DocumentDTO> livres1 = clientService.rechercheParTitre("titre");
        //Todo Recherche de auteur:
            List<DocumentDTO> livres2 = clientService.rechercheParAuteur("auteur");
        //Todo Recherche de année:
            List<DocumentDTO> livres3 = clientService.rechercheParAnne(2000);
        //Todo Recherche de genre:
            List<DocumentDTO> livres4 = clientService.rechercheParGenre(Genres.roman);
        //Todo emprunt d'un document si plus d'un exemplaire dispo:
            int empId = clientService.emprunter(clId,liId);
        //Todo Retour d'un emprunt
            livres2 = clientService.rechercheParTitre("titre");
            clientService.retourner(clId,empId);
        //Todo Obtenir la liste des dates de retour des documents
            List<DateDTO> dates = clientService.getDatesDeRetour(clId);
        //Todo liste d'emprunts:
            List<EmpruntDTO> emprunt = clientService.getEmprunts(clId);
        //Todo interface web
    }
}
