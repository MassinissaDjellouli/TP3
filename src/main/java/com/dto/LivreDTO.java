package com.dto;

import com.models.enums.Genres;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class LivreDTO extends DocumentDTO{
    @NotBlank
    @NotNull
    @Min(1)
    private String nbPages;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^roman$|^magazine$|^manuel$|^etude$")
    private String genre;
}
