package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmpruntDTO {
    private String clientName;
    private String empruntDate;
    private String returnDate;
    private String documentName;
}
