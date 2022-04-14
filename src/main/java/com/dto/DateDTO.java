package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DateDTO {
    private String clientName;
    private String documentName;
    private String dateEmprunt;
    private String dateRetour;
}
