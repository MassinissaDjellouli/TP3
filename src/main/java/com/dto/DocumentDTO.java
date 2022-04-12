package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public abstract class DocumentDTO {
    private String titre;
    private String auteur;
    private String editeur;
    private String anneeDePublication;
    private String tempsEmprunt;
    private String nbExemplaires;
}
