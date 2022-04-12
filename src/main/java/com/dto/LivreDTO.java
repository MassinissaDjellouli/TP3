package com.dto;

import com.models.enums.Genres;
import lombok.Data;

@Data
public class LivreDTO extends DocumentDTO{
    private String nbPages;
    private String genre;
}
