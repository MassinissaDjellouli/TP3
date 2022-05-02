package com.service;


import com.dto.DocumentDTO;
import com.models.documents.Livre;
import com.models.documents.Media;
import com.models.enums.Genres;
import com.models.enums.MediaType;
import com.repository.ClientRepository;
import com.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
    @Autowired
    DocumentRepository documentRepository;
    public int saveLivre(String titre, String auteur, String editeur, int anne, int tmpEmprunt, int nbExemplaires, int nbPages, Genres genre) {
        Livre livre = Livre.builder().titre(titre).auteur(auteur).editeur(editeur)
                .anneeDePublication(anne).tempsEmprunt(tmpEmprunt)
                .nbExemplaires(nbExemplaires).nbPages(nbPages).genre(genre).build();
        documentRepository.save(livre);
        System.out.println(livre.getDocumentId());
        return livre.getDocumentId();
    }

    public int saveMedia(String titre, String auteur, String editeur, int anne, int tmpEmprunt, int nbExemplaires, String duree, MediaType type) {
        Media media = Media.builder().titre(titre).auteur(auteur).editeur(editeur)
                .anneeDePublication(anne).tempsEmprunt(tmpEmprunt)
                .nbExemplaires(nbExemplaires).duree(duree).type(type).build();
        documentRepository.save(media);
        System.out.println(media.getDocumentId());
        return media.getDocumentId();
    }
}
