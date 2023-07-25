package ru.league.kafkaedu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties
public class PaperlessTaskResult {
    private String instanceId;
    private String siebelId;
    private TaskDto task;
    private List<DocumentDto> documents;
}
