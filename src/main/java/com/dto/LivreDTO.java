package com.dto;

import com.models.enums.Genres;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LivreDTO extends DocumentDTO{
    private String nbPages;
    private String genre;
}
