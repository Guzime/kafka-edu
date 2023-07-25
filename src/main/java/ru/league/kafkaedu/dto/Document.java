package ru.league.kafkaedu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Document {
    private String id;
    private String documentType;
}