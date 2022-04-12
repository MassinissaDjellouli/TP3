package com.dto;

import com.models.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaDTO extends DocumentDTO{
    private String duree;
    private String type;
}
