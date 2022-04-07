package com;

import com.Repository.BiblioDAO;
import com.Models.Documents.Livre;
import com.Models.Documents.Media;
import com.Models.Enums.Genres;
import com.Models.Enums.MediaType;
import com.Models.Users.Client;
import com.Service.ClientService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientService service = new ClientService(new BiblioDAO());
        //Todo Enregistrement d'un client:
        service.saveNewClient("client","3","num");
        List<Client> clients = new ArrayList<>(service.getAllClients());
        //Todo Ajout d'un livre:
        service.saveNewLivre("l","a1","e1",2000,2,23, Genres.roman);
        service.saveNewLivre("l2","a2","e2",2000,3,24,Genres.etude);
        service.saveNewLivre("l2","a2","e2",2000,3,24,Genres.etude);
        service.saveNewLivre("l3","a1","e3",2003,4,25,Genres.magazine);
        //Todo Ajout d'un media:
        service.saveNewMedia("m1","ma3","me3",1980,4,"2h", MediaType.dvd);
        service.saveNewMedia("m1","ma3","me3",1980,4,"2h", MediaType.dvd);
        service.saveNewMedia("m3","ma3","me3",1981,2,"14min", MediaType.cd);
        //Todo Recherche de livre titre:
        List<Livre> livreTitre = service.rechercheLivreTitre("l2");
        printAll(livreTitre);
        //Todo Recherche de livre auteur:
        List<Livre> livreAuteur = service.rechercheLivreAuteur("a1");
        printAll(livreAuteur);
        //Todo Recherche de livre année:
        List<Livre> livreAnnee = service.rechercheLivreAnne(2000);
        printAll(livreAnnee);
        //Todo Recherche de livre genre:
        List<Livre> livreGenre = service.rechercheLivreGenre(Genres.roman);
        printAll(livreGenre);
        // Todo Recherche de media titre:
        List<Media> mediaTitre = service.rechercheMediaTitre("m1");
        printAll(mediaTitre);
        //Todo Recherche de media auteur:
        List<Media> mediaAuteur = service.rechercheMediaAuteur("ma3");
        printAll(mediaAuteur);
        //Todo Recherche de media année:
        List<Media> mediaAnne = service.rechercheMediaAnne(1981);
        printAll(mediaAnne);
        //Todo Recherche de media type:
        List<Media> mediaType = service.rechercheMediaType(MediaType.dvd);
        printAll(mediaType);
        //Todo emprunt d'un document:
        service.emprunter(clients.get(0).getClientNumber(),livreAnnee.get(0));
        //Todo liste d'emprunts:
        System.out.println(service.getClientEmprunts(clients.get(0).getClientNumber()));
        System.out.println();
    }
    private static void printAll(List list){
        for(Object obj : list){
            System.out.println(obj);
        }
    }
}
