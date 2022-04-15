package com.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class DocumentDTO {
    final static int CURRENT_YEAR = 2022;
    @NotNull
    @NotBlank
    @Size(min=3,max = 200)
    private String titre;
    @NotNull
    @NotBlank
    @Size(min=3,max = 200)
    private String auteur;
    @NotNull
    @NotBlank
    @Size(min=3,max = 200)
    private String editeur;
    @NotNull
    @NotBlank
    @Max(CURRENT_YEAR)
    private String anneeDePublication;
    @NotNull
    @NotBlank
    @Min(1)
    @Max(3)
    private String tempsEmprunt;
    @NotNull
    @NotBlank
    @Min(1)
    private String nbExemplaires;
}
