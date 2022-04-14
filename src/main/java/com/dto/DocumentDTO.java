package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class DocumentDTO {
    private String titre;
    private String auteur;
    private String editeur;
    private String anneeDePublication;
    private String tempsEmprunt;
    private String nbExemplaires;
}
