package com.dto;

import com.models.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class MediaDTO extends DocumentDTO{
    private String duree;
    private String type;
}
