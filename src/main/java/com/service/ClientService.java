package com.service;

import com.dto.*;
import com.models.Emprunt;
import com.models.documents.Documents;
import com.models.documents.Livre;
import com.models.documents.Media;
import com.models.enums.Genres;
import com.models.users.Client;
import com.repository.ClientRepository;
import com.repository.DocumentRepository;
import com.repository.EmpruntRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private EmpruntRepository empruntRepository;


    public int saveClient(String name, String adress, String phone) {
        Client client = Client.builder().clientName(name).clientAdress(adress).clientPhone(phone).build();
        clientRepository.save(client);
        return client.getClientNumber();
    }

    public List<DocumentDTO> rechercheParTitre(String titre) {
        return ModelToDTOTransformer.documentListToDTO(handleOptionalList(documentRepository.findAllByTitreContaining(titre)));
    }

    public List<DocumentDTO> rechercheParAuteur(String auteur) {
        return ModelToDTOTransformer.documentListToDTO(handleOptionalList(documentRepository.findAllByAuteur(auteur)));
    }

    public List<DocumentDTO> rechercheParAnne(int anne) {
        return ModelToDTOTransformer.documentListToDTO(handleOptionalList(documentRepository.findAllByAnneeDePublication(anne)));
    }

    public List<DocumentDTO> rechercheParGenre(Genres genre) {
        return ModelToDTOTransformer.documentListToDTO(handleOptionalList(documentRepository.findAllByGenre(genre)));
    }
    @Transactional
    public int emprunter(int cliId,int docId) throws IllegalArgumentException{
        Documents document = handleOptional(documentRepository.findById(docId));
        if (document.getNbExemplaires() == 0){
            throw new IllegalArgumentException();
        }
        Client client = handleOptional(clientRepository.findById(cliId));
        Emprunt emprunt = Emprunt.builder()
                .client(client)
                .document(document)
                .dateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now().plusWeeks(document.getTempsEmprunt()))
                .returned(false)
                .build();
        document.setNbExemplaires(document.getNbExemplaires() - 1);
        documentRepository.save(document);
        empruntRepository.save(emprunt);
        return emprunt.getId();
    }

    public void retourner(long empId) {
    }

    public void payerFrais(long clientId, long montant) {
    }

    public List<DateDTO> getDatesDeRetour(long clientId) {
        return null;
    }

    public List<EmpruntDTO> getEmprunts(long clientId) {
        return null;
    }

    public List<DetteDTO> getFrais(long clientId) {
        return null;
    }

    private <T> List<T> handleOptionalList(Optional<List<T>> optional){
        if (optional.isEmpty()) return Collections.emptyList();
        return optional.get();
    }
    private <T> T handleOptional(Optional<T> optional){
        if (optional.isEmpty()) throw new IllegalArgumentException();
        return optional.get();
    }
//
//    public ClientService() {
//    }
//
//    public void saveNewClient(String name,String adress, String phone){
//        Dette dette = new Dette();
//        Client toSave = Client.builder()
//                        .clientName(name)
//                        .clientPhone(phone)
//                        .clientAdress(adress)
//                        .build();
//        dette.setClient(toSave);
//        toSave.setDette(dette);
//        clientRepository.save(toSave);
//    }
//
//    public void addDetteToClient(int id, float montant){
//
//        try {
//            Client client = clientRepository.findClientById(id);
//            Dette oldDette = client.getDette();
//            Dette dette = Dette.builder()
//                    .montant(montant)
//                    .dateDebut(LocalDateTime.now())
//                    .id(oldDette.getId())
//                    .build();
//            client.setDette(dette);
//            dette.setClient(client);
//            clientRepository.merge(dette);
//            clientRepository.merge(client);
//        }catch (IllegalArgumentException e){
//            clientRepository.handleException("Une erreur est survenue avec la base de donnée");
//        }
//    }
//
//    public Client getClientById(int id){
//        return clientRepository.findClientByIdWEmprunts(id);
//    }
//
//    public List<Emprunt> getClientEmprunts(int clientId){
//        Client client = getClientById(clientId);
//        return client.getEmprunts();
//    }
//
//    public Set<Client> getAllClients(){
//        return clientRepository.findAllClient();
//    }
//
//    public Set<Client> getAllClientsWEmprunt(){
//        return clientRepository.findAllClientWEmprunts();
//    }
//
//    public void saveNewLivre(String titre, String auteur,
//                             String editeur, int anneDePublication,
//                             int nbExemplaire, int nbPages, Genres genre){
//        Livre toSave = Livre.builder()
//                .titre(titre)
//                .auteur(auteur)
//                .editeur(editeur)
//                .anneeDePublication(anneDePublication)
//                .nbExemplaires(nbExemplaire)
//                .nbPages(nbPages)
//                .tempsEmprunt(3)
//                .genre(genre)
//                .build();
//        if (checkIfLivrePresent(toSave)){
//            addExemplaire(toSave, 1);
//        }else {
//            clientRepository.save(toSave);
//        }
//    }
//
//    public void saveNewMedia(String titre, String auteur,
//                             String editeur, int anneDePublication,
//                             int nbExemplaire, String duree, MediaType type){
//        Media toSave = Media.builder()
//                .titre(titre)
//                .auteur(auteur)
//                .editeur(editeur)
//                .anneeDePublication(anneDePublication)
//                .nbExemplaires(nbExemplaire)
//                .duree(duree)
//                .type(type)
//                .tempsEmprunt(Documents.setMediaTempsEmprunts(type))
//                .build();
//        if (checkIfMediaPresent(toSave)){
//            addExemplaire(toSave, 1);
//        }else {
//            clientRepository.save(toSave);
//        }
//    }
//
//    public List<Livre> rechercheLivreTitre(String titre){
//        Set<Livre> livres = clientRepository.findAllLivre();
//        Set<Livre> toReturn = new HashSet<>();
//        for (Livre livre : livres){
//            if (livre.getTitre().contains(titre)){
//                toReturn.add(livre);
//            }
//        }
//        return new ArrayList<>(toReturn);
//    }
//
//    public List<Livre> rechercheLivreAuteur(String auteur){
//        Set<Livre> livres = clientRepository.findAllLivre();
//        Set<Livre> toReturn = new HashSet<>();
//        for (Livre livre : livres){
//            if (livre.getAuteur().equals(auteur)){
//                toReturn.add(livre);
//            }
//        }
//        return new ArrayList<>(toReturn);
//    }
//
//    public List<Livre> rechercheLivreAnne(int annee){
//        Set<Livre> livres = clientRepository.findAllLivre();
//        Set<Livre> toReturn = new HashSet<>();
//        for (Livre livre : livres){
//            if (livre.getAnneeDePublication() == annee){
//                toReturn.add(livre);
//            }
//        }
//        return new ArrayList<>(toReturn);
//    }
//
//    public List<Livre> rechercheLivreGenre(Genres genre){
//        Set<Livre> livres = clientRepository.findAllLivre();
//        Set<Livre> toReturn = new HashSet<>();
//        for (Livre livre : livres){
//            if (livre.getGenre() == (genre)){
//                toReturn.add(livre);
//            }
//        }
//        return new ArrayList<>(toReturn);
//    }
//
//    public List<Media> rechercheMediaTitre(String titre){
//        Set<Media> medias = clientRepository.findAllMedia();
//        Set<Media> toReturn = new HashSet<>();
//        for (Media media : medias){
//            if (media.getTitre().contains(titre)){
//                toReturn.add(media);
//            }
//        }
//        return new ArrayList<>(toReturn);
//    }
//
//    public List<Media> rechercheMediaAuteur(String auteur){
//        Set<Media> medias = clientRepository.findAllMedia();
//        Set<Media> toReturn = new HashSet<>();
//        for (Media media : medias){
//            if (media.getAuteur().equals(auteur)){
//                toReturn.add(media);
//            }
//        }
//        return new ArrayList<>(toReturn);
//    }
//
//    public List<Media> rechercheMediaAnne(int annee){
//        Set<Media> medias = clientRepository.findAllMedia();
//        Set<Media> toReturn = new HashSet<>();
//        for (Media media : medias){
//            if (media.getAnneeDePublication() == annee ){
//                toReturn.add(media);
//            }
//        }
//        return new ArrayList<>(toReturn);
//    }
//
//    public List<Media> rechercheMediaType(MediaType type){
//        Set<Media> medias = clientRepository.findAllMedia();
//        Set<Media> toReturn = new HashSet<>();
//        for (Media media : medias){
//            if (media.getType() == type){
//                toReturn.add(media);
//            }
//        }
//        return new ArrayList<>(toReturn);
//    }
//
//    public void emprunter(int clientId,Documents document){
//        Client client = clientRepository.findClientByIdWEmprunts(clientId);
//        try {
//            throwClientInexistant(client);
//            throwClientEmpruntsMax(client);
//            throwClientAmende(client);
//            throwDocumentInexistant(document);
//        }catch (IllegalArgumentException e){
//            return;
//        }
//        Emprunt emprunt = Emprunt.builder()
//                .document(document)
//                .client(client)
//                .dateTime(LocalDateTime.now())
//                .returnDateTime(LocalDateTime.now().plusWeeks(document.getTempsEmprunt()))
//                .build();
//        List<Emprunt> empruntList = client.getEmprunts();
//        empruntList.add(emprunt);
//        client.setEmprunts(empruntList);
//        clientRepository.merge(client);
//            addExemplaire(document,-1);
//        clientRepository.save(emprunt);
//    }
//
//    private void throwClientEmpruntsMax(Client client){
//        List<Emprunt> clientEmprunts = getClientEmprunts(client.getClientNumber());
//        if (clientEmprunts.size() >= 3){
//            System.out.println("La limite d'emprunts est atteinte pour le client");
//            throw new IllegalArgumentException();
//        }
//    }
//
//    private void throwClientAmende(Client client){
//        Dette dette = client.getDette();
//        if (dette.getMontant() > 0){
//            System.out.println("Le client a une dette a regler");
//            throw new IllegalArgumentException();
//        }
//    }
//
//    private void throwDocumentInexistant(Documents document){
//        if (document instanceof Livre && checkIfLivrePresent((Livre) document) && document.getNbExemplaires() > 0){
//            return;
//        }else if (document instanceof Media && checkIfMediaPresent((Media) document) && document.getNbExemplaires() > 0){
//            return;
//        }
//        System.out.println("Le document recherché n'existe pas");
//
//        throw new IllegalArgumentException();
//    }
//
//    private void throwClientInexistant(Client client){
//        if (clientRepository.findClientById(client.getClientNumber()) != null){
//            return;
//        }
//        System.out.println("Le client recherché n'existe pas");
//        throw new IllegalArgumentException();
//    }
//
//    private void addExemplaire(Documents document, int amount) {
//        document.setDocumentId(getDocumentId(document));
//        document.setNbExemplaires(document.getNbExemplaires() + amount);
//        clientRepository.merge(document);
//    }
//
//    private int getLivreId(Livre toFind){
//        if (checkIfLivrePresent(toFind)){
//            Set<Livre> livres = clientRepository.findAllLivre();
//            for (Livre livre : livres){
//                if(livre.equals(toFind)){
//                    return livre.getDocumentId();
//                }
//            }
//        }
//        return toFind.getDocumentId();
//    }
//
//    private int getDocumentId(Documents toFind){
//        if (toFind instanceof Livre){
//            return getLivreId((Livre) toFind);
//        }else {
//            return getMediaId((Media) toFind);
//        }
//    }
//
//    private int getMediaId(Media toFind){
//        if (checkIfMediaPresent(toFind)){
//            Set<Media> mediaSet= clientRepository.findAllMedia();
//            for (Media media : mediaSet){
//                if(media.equals(toFind)){
//                    return media.getDocumentId();
//                }
//            }
//        }
//        return toFind.getDocumentId();
//    }
//
//    private boolean checkIfLivrePresent(Livre toCheck){
//        Set<Livre> livres = clientRepository.findAllLivre();
//        for (Livre livre : livres){
//            if(livre.equals(toCheck)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean checkIfMediaPresent(Media toCheck){
//        Set<Media> mediaSet = clientRepository.findAllMedia();
//        for (Media media : mediaSet){
//            if(media.equals(toCheck)){
//                return true;
//            }
//        }
//        return false;
//    }
}
