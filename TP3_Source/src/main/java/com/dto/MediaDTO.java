package com.dto;

import com.models.enums.MediaType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MediaDTO extends DocumentDTO{
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]*h[0-5][0-9]min")
    private String duree;
    @Pattern(regexp = "^cd$|^dvd$")
    private String type;
}
