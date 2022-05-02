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
        System.out.println(client.getClientNumber());
        return client.getClientNumber();
    }

    public List<Documents> rechercheGlobale(){
        return documentRepository.findAll();
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
        if (client.getEmprunts().size() == 3){
            throw new IllegalArgumentException();
        }
        Emprunt emprunt = Emprunt.builder()
                .client(client)
                .document(document)
                .dateTime(LocalDateTime.now())
                .returnDateTime(LocalDateTime.now().plusWeeks(document.getTempsEmprunt()))
                .returned(false)
                .build();
        document.setNbExemplaires(document.getNbExemplaires() - 1);
        List<Emprunt> emprunts = client.getEmprunts();
        emprunts.add(emprunt);
        client.setEmprunts(emprunts);
        documentRepository.save(document);
        empruntRepository.save(emprunt);
        System.out.println(emprunt.getId());
        return emprunt.getId();
    }

    @Transactional
    public void retourner(int clId,int empId) throws IllegalArgumentException{
        Emprunt emprunt = handleOptional(empruntRepository.findById(empId));
        Client client = handleOptional(clientRepository.findByIdWithEmprunts(clId));
        Documents document = emprunt.getDocument();
        if(emprunt.isReturned()) throw new IllegalArgumentException();
        emprunt.setReturned(true);
        List<Emprunt> emprunts = client.getEmprunts();
        emprunts.remove(emprunt);
        client.setEmprunts(emprunts);
        document.setNbExemplaires(document.getNbExemplaires() + 1);
        documentRepository.save(document);
        empruntRepository.save(emprunt);
        System.out.println(emprunt.getId());
    }

    public List<DateDTO> getDatesDeRetour(int clientId) {
        Client client = handleOptional(clientRepository.findByIdWithEmprunts(clientId));
        List<Emprunt> emprunts = client.getEmprunts();
        return ModelToDTOTransformer.empruntListToDateDtoList(emprunts);
    }

    public List<EmpruntDTO> getEmprunts(int clientId) {
        Client client = handleOptional(clientRepository.findByIdWithEmprunts(clientId));
        List<Emprunt> emprunts = client.getEmprunts();
        return ModelToDTOTransformer.empruntListToEmpruntsDtoList(emprunts);
    }

    private <T> List<T> handleOptionalList(Optional<List<T>> optional) throws IllegalArgumentException{
        if (optional.isEmpty()) return Collections.emptyList();
        return optional.get();
    }

    private <T> T handleOptional(Optional<T> optional) throws IllegalArgumentException{
        if (optional.isEmpty()) throw new IllegalArgumentException();
        return optional.get();
    }
}
