package com.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class EmpruntDTO {
    private String clientName;
    private String empruntDate;
    private String returnDate;
    private String documentName;
}
