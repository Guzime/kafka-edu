package ru.league.kafkaedu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class Paperless {
    private String instanceId;
    private String siebelId;
    private Task task;
    private List<Document> documents;
}
