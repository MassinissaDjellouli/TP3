package com.dto;

import com.models.Dette;
import com.models.Emprunt;
import com.models.documents.Documents;
import com.models.documents.Livre;
import com.models.documents.Media;

import java.util.ArrayList;
import java.util.List;

public class ModelToDTOTransformer {
    public static EmpruntDTO empruntToDTO(Emprunt emprunt){
        return null;
    }
    public static DetteDTO detteToDTO(Dette dette){
        return null;
    }
    public static DateDTO dateEmpruntToDTO(Emprunt emprunt){
        return null;
    }
    public static LivreDTO LivreToDTO(Livre livre){
        return LivreDTO.builder()
                .titre(livre.getTitre())
                .auteur(livre.getAuteur())
                .editeur(livre.getEditeur())
                .anneeDePublication(Integer.toString(livre.getAnneeDePublication()))
                .tempsEmprunt(Integer.toString(livre.getTempsEmprunt()))
                .nbExemplaires(Integer.toString(livre.getNbExemplaires()))
                .nbPages(Integer.toString(livre.getNbPages()))
                .genre(livre.getGenre().toString())
                .build();
    }
    public static MediaDTO mediaToDTO(Media media){
        return MediaDTO.builder()
                .titre(media.getTitre())
                .auteur(media.getAuteur())
                .editeur(media.getEditeur())
                .anneeDePublication(Integer.toString(media.getAnneeDePublication()))
                .tempsEmprunt(Integer.toString(media.getTempsEmprunt()))
                .nbExemplaires(Integer.toString(media.getNbExemplaires()))
                .duree(media.getDuree())
                .type(media.getType().toString())
                .build();
    }

    public static List<DocumentDTO> documentListToDTO(List<Documents> documentsList) {
        List<DocumentDTO> documentDTOList = new ArrayList<>();
        for (Documents doc:documentsList) {
            if(doc instanceof Livre){
                documentDTOList.add(LivreToDTO((Livre) doc));
            }else if(doc instanceof Media){
                documentDTOList.add(mediaToDTO((Media) doc));
            }
        }
        return documentDTOList;
    }
    public static DateDTO empruntToDateDTO(Emprunt emprunt){
        return DateDTO.builder()
                .dateEmprunt(emprunt.getDateTime().format(DateDTO.DATE_TIME_FORMATTER))
                .dateRetour(emprunt.getReturnDateTime().format(DateDTO.DATE_TIME_FORMATTER))
                .clientName(emprunt.getClient().getClientName())
                .documentName(emprunt.getDocument().getTitre())
                .build();
    }
    public static List<DateDTO> empruntListToDateDtoList(List<Emprunt> emprunts) {
        List<DateDTO> dateDTOList = new ArrayList<>();
        for(Emprunt emprunt : emprunts){
            dateDTOList.add(empruntToDateDTO(emprunt));
        }
        return dateDTOList;
    }
}
