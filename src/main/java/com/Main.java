package com;

import com.dto.*;
import com.models.enums.Genres;
import com.models.enums.MediaType;
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
    private static void printAll(List list){
        for(Object obj : list){
            System.out.println(obj);
        }
    }

    @Override
    public void run(String... args){

        //Todo Enregistrement d'un client:
        long clId = clientService.saveClient("name","adress","phone");
        //Todo Ajout d'un livre:
        long liId = employeeService.saveLivre("titre", "auteur numero 2", "editeur", 2000, 3, 30, 100, Genres.roman);
        long li2Id = employeeService.saveLivre("titre numero 2", "auteur", "editeur", 2000, 3, 30, 100, Genres.roman);
        //Todo Ajout d'un media:
        long meId = employeeService.saveMedia("titre","auteur","editeur",2000,3,30,"40min", MediaType.dvd);
        //Todo Recherche de titre:
            List<DocumentDTO> livres1 = clientService.rechercheParTitre("titre");
        //Todo Recherche de auteur:
            List<DocumentDTO> livres2 = clientService.rechercheParAuteur("auteur");
        //Todo Recherche de année:
            List<DocumentDTO> livres3 = clientService.rechercheParAnne(2000);
        //Todo Recherche de genre:
            List<DocumentDTO> livres4 = clientService.rechercheParGenre(Genres.roman);
        //Todo emprunt d'un document si plus d'un exemplaire dispo:
            long empId = clientService.emprunter(clId,liId);
        //Todo Retour d'un emprunt
            clientService.retourner(empId);
        //Todo Obtenir la liste des dates de retour des documents
            List<DateDTO> dates = clientService.getDatesDeRetour(clId);
        //Todo Obtenir la liste des frais
            List<DetteDTO> frais = clientService.getFrais(clId);
        //Todo liste d'emprunts:1
            List<EmpruntDTO> emprunt = clientService.getEmprunts(clId);
        //Todo Traitement des amendes

        //Todo interface web
    }
}
