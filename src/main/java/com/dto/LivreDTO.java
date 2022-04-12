package com.dto;

import com.models.enums.Genres;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class LivreDTO extends DocumentDTO{
    private String nbPages;
    private String genre;
}
