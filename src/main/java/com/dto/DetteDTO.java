package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetteDTO {
    private String clientName;
    private String montant;
    private String date;
}
