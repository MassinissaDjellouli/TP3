package com.Service;

import com.Repository.BiblioDAO;
import com.Models.Dette;
import com.Models.Documents.Documents;
import com.Models.Documents.Livre;
import com.Models.Documents.Media;
import com.Models.Emprunt;
import com.Models.Enums.Genres;
import com.Models.Enums.MediaType;
import com.Models.Users.Client;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientService {
    private BiblioDAO DB;

    public ClientService(BiblioDAO DBClient) {
        this.DB = DBClient;
    }

    public void saveNewClient(String name,String adress, String phone){
        Dette dette = new Dette();
        Client toSave = Client.builder()
                        .clientName(name)
                        .clientPhone(phone)
                        .clientAdress(adress)
                        .build();
        dette.setClient(toSave);
        toSave.setDette(dette);
        DB.save(toSave);
    }

    public void addDetteToClient(int id, float montant){

        try {
            Client client = DB.findClientById(id);
            Dette oldDette = client.getDette();
            Dette dette = Dette.builder()
                    .montant(montant)
                    .dateDebut(LocalDateTime.now())
                    .id(oldDette.getId())
                    .build();
            client.setDette(dette);
            dette.setClient(client);
            DB.merge(dette);
            DB.merge(client);
        }catch (IllegalArgumentException e){
            DB.handleException("Une erreur est survenue avec la base de donnée");
        }
    }

    public Client getClientById(int id){
        return DB.findClientByIdWEmprunts(id);
    }

    public List<Emprunt> getClientEmprunts(int clientId){
        Client client = getClientById(clientId);
        return client.getEmprunts();
    }

    public Set<Client> getAllClients(){
        return DB.findAllClient();
    }

    public Set<Client> getAllClientsWEmprunt(){
        return DB.findAllClientWEmprunts();
    }

    public void saveNewLivre(String titre, String auteur,
                             String editeur, int anneDePublication,
                             int nbExemplaire, int nbPages, Genres genre){
        Livre toSave = Livre.builder()
                .titre(titre)
                .auteur(auteur)
                .editeur(editeur)
                .anneeDePublication(anneDePublication)
                .nbExemplaires(nbExemplaire)
                .nbPages(nbPages)
                .tempsEmprunt(3)
                .genre(genre)
                .build();
        if (checkIfLivrePresent(toSave)){
            addExemplaire(toSave, 1);
        }else {
            DB.save(toSave);
        }
    }

    public void saveNewMedia(String titre, String auteur,
                             String editeur, int anneDePublication,
                             int nbExemplaire, String duree, MediaType type){
        Media toSave = Media.builder()
                .titre(titre)
                .auteur(auteur)
                .editeur(editeur)
                .anneeDePublication(anneDePublication)
                .nbExemplaires(nbExemplaire)
                .duree(duree)
                .type(type)
                .tempsEmprunt(Documents.setMediaTempsEmprunts(type))
                .build();
        if (checkIfMediaPresent(toSave)){
            addExemplaire(toSave, 1);
        }else {
            DB.save(toSave);
        }
    }

    public List<Livre> rechercheLivreTitre(String titre){
        Set<Livre> livres = DB.findAllLivre();
        Set<Livre> toReturn = new HashSet<>();
        for (Livre livre : livres){
            if (livre.getTitre().contains(titre)){
                toReturn.add(livre);
            }
        }
        return new ArrayList<>(toReturn);
    }

    public List<Livre> rechercheLivreAuteur(String auteur){
        Set<Livre> livres = DB.findAllLivre();
        Set<Livre> toReturn = new HashSet<>();
        for (Livre livre : livres){
            if (livre.getAuteur().equals(auteur)){
                toReturn.add(livre);
            }
        }
        return new ArrayList<>(toReturn);
    }

    public List<Livre> rechercheLivreAnne(int annee){
        Set<Livre> livres = DB.findAllLivre();
        Set<Livre> toReturn = new HashSet<>();
        for (Livre livre : livres){
            if (livre.getAnneeDePublication() == annee){
                toReturn.add(livre);
            }
        }
        return new ArrayList<>(toReturn);
    }

    public List<Livre> rechercheLivreGenre(Genres genre){
        Set<Livre> livres = DB.findAllLivre();
        Set<Livre> toReturn = new HashSet<>();
        for (Livre livre : livres){
            if (livre.getGenre() == (genre)){
                toReturn.add(livre);
            }
        }
        return new ArrayList<>(toReturn);
    }

    public List<Media> rechercheMediaTitre(String titre){
        Set<Media> medias = DB.findAllMedia();
        Set<Media> toReturn = new HashSet<>();
        for (Media media : medias){
            if (media.getTitre().contains(titre)){
                toReturn.add(media);
            }
        }
        return new ArrayList<>(toReturn);
    }

    public List<Media> rechercheMediaAuteur(String auteur){
        Set<Media> medias = DB.findAllMedia();
        Set<Media> toReturn = new HashSet<>();
        for (Media media : medias){
            if (media.getAuteur().equals(auteur)){
                toReturn.add(media);
            }
        }
        return new ArrayList<>(toReturn);
    }

    public List<Media> rechercheMediaAnne(int annee){
        Set<Media> medias = DB.findAllMedia();
        Set<Media> toReturn = new HashSet<>();
        for (Media media : medias){
            if (media.getAnneeDePublication() == annee ){
                toReturn.add(media);
            }
        }
        return new ArrayList<>(toReturn);
    }

    public List<Media> rechercheMediaType(MediaType type){
        Set<Media> medias = DB.findAllMedia();
        Set<Media> toReturn = new HashSet<>();
        for (Media media : medias){
            if (media.getType() == type){
                toReturn.add(media);
            }
        }
        return new ArrayList<>(toReturn);
    }

    public void emprunter(int clientId,Documents document){
        Client client = DB.findClientByIdWEmprunts(clientId);
        try {
            throwClientInexistant(client);
            throwClientEmpruntsMax(client);
            throwClientAmende(client);
            throwDocumentInexistant(document);
        }catch (IllegalArgumentException e){
            return;
        }
        Emprunt emprunt = Emprunt.builder()
                .document(document)
                .client(client)
                .dateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now().plusWeeks(document.getTempsEmprunt()))
                .build();
        List<Emprunt> empruntList = client.getEmprunts();
        empruntList.add(emprunt);
        client.setEmprunts(empruntList);
        DB.merge(client);
            addExemplaire(document,-1);
        DB.save(emprunt);
    }

    private void throwClientEmpruntsMax(Client client){
        List<Emprunt> clientEmprunts = getClientEmprunts(client.getClientNumber());
        if (clientEmprunts.size() >= 3){
            System.out.println("La limite d'emprunts est atteinte pour le client");
            throw new IllegalArgumentException();
        }
    }

    private void throwClientAmende(Client client){
        Dette dette = client.getDette();
        if (dette.getMontant() > 0){
            System.out.println("Le client a une dette a regler");
            throw new IllegalArgumentException();
        }
    }

    private void throwDocumentInexistant(Documents document){
        if (document instanceof Livre && checkIfLivrePresent((Livre) document) && document.getNbExemplaires() > 0){
            return;
        }else if (document instanceof Media && checkIfMediaPresent((Media) document) && document.getNbExemplaires() > 0){
            return;
        }
        System.out.println("Le document recherché n'existe pas");

        throw new IllegalArgumentException();
    }

    private void throwClientInexistant(Client client){
        if (DB.findClientById(client.getClientNumber()) != null){
            return;
        }
        System.out.println("Le client recherché n'existe pas");
        throw new IllegalArgumentException();
    }

    private void addExemplaire(Documents document, int amount) {
        document.setDocumentId(getDocumentId(document));
        document.setNbExemplaires(document.getNbExemplaires() + amount);
        DB.merge(document);
    }

    private int getLivreId(Livre toFind){
        if (checkIfLivrePresent(toFind)){
            Set<Livre> livres = DB.findAllLivre();
            for (Livre livre : livres){
                if(livre.equals(toFind)){
                    return livre.getDocumentId();
                }
            }
        }
        return toFind.getDocumentId();
    }

    private int getDocumentId(Documents toFind){
        if (toFind instanceof Livre){
            return getLivreId((Livre) toFind);
        }else {
            return getMediaId((Media) toFind);
        }
    }

    private int getMediaId(Media toFind){
        if (checkIfMediaPresent(toFind)){
            Set<Media> mediaSet= DB.findAllMedia();
            for (Media media : mediaSet){
                if(media.equals(toFind)){
                    return media.getDocumentId();
                }
            }
        }
        return toFind.getDocumentId();
    }

    private boolean checkIfLivrePresent(Livre toCheck){
        Set<Livre> livres = DB.findAllLivre();
        for (Livre livre : livres){
            if(livre.equals(toCheck)){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfMediaPresent(Media toCheck){
        Set<Media> mediaSet = DB.findAllMedia();
        for (Media media : mediaSet){
            if(media.equals(toCheck)){
                return true;
            }
        }
        return false;
    }
}
