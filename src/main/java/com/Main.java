package com;

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
        //Todo Enregistrement d'un client:

        //Todo Ajout d'un livre:

        //Todo Ajout d'un media:

        //Todo Recherche de livre titre:

        //Todo Recherche de livre auteur:

        //Todo Recherche de livre année:

        //Todo Recherche de livre genre:

        // Todo Recherche de media titre:

        //Todo Recherche de media auteur:

        //Todo Recherche de media année:

        //Todo Recherche de media type:

        //Todo emprunt d'un document si plus d'un exemplaire dispo:

        //Todo Retour d'un emprunt

        //Todo Paiement des frais par le client

        //Todo Obtenir la liste des dates de retour des documents

        //Todo Obtenir la liste des frais

        //Todo liste d'emprunts:

        //Todo Traitement des amendes

        //Todo interface web
    }
}
