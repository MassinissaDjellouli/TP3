package com;

import com.dto.DateDTO;
import com.dto.DocumentDTO;
import com.dto.EmpruntDTO;
import com.dto.FraisDTO;
import com.models.enums.Genres;
import com.models.enums.MediaType;
import com.service.ClientService;
import com.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
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
        ClientService clientService = new ClientService();
        EmployeeService employeeService = new EmployeeService();
        //Todo Enregistrement d'un client:
        long clId = clientService.saveClient("name","adress","phone");
        //Todo Ajout d'un livre:
        long emId = employeeService.saveLivre("titre", "auteur", "editeur", 2000, 3, 30, 100, Genres.roman);
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
            long empId = clientService.emprunter(2);
        //Todo Retour d'un emprunt
            clientService.retourner(2);
        //Todo Obtenir la liste des dates de retour des documents
            List<DateDTO> dates = clientService.getDatesDeRetour(3);
        //Todo Obtenir la liste des frais
            List<FraisDTO> frais = clientService.getFrais(3);
        //Todo liste d'emprunts:1
            List<EmpruntDTO> emprunt = clientService.getEmprunts(3);
        //Todo Traitement des amendes

        //Todo interface web
    }
}
