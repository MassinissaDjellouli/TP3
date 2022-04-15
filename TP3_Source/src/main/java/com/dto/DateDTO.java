package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Data
@Builder
public class DateDTO {
    private String documentName;
    private String dateRetour;
}
