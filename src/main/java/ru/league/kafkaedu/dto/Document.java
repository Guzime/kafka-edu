package ru.league.kafkaedu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @JsonProperty("id")
    private String id;
    @JsonProperty("documentType")
    private String documentType;
}