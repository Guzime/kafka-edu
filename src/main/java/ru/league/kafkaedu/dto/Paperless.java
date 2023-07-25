package ru.league.kafkaedu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paperless {
    @JsonProperty("instanceId")
    private String instanceId;
    @JsonProperty("siebelId")
    private String siebelId;
    @JsonProperty("task")
    private Task task;
    @JsonProperty("documents")
    private List<Document> documents;
}
