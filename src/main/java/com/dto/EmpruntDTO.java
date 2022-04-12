package com.dto;

import lombok.Data;

@Data
public class EmpruntDTO {
    private String clientName;
    private String empruntDate;
    private String returnDate;
    private String documentName;
}
