package com.dto;

import com.models.enums.MediaType;
import lombok.Data;

@Data
public class MediaDTO extends DocumentDTO{
    private String duree;
    private String type;
}
