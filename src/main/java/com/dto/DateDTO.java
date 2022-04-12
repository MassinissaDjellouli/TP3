package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DateDTO {
    private String clientName;
    private String documentName;
    private String dateEmprunt;
}
