package ru.league.kafkaedu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Task {
    private String id;
    private String requestId;
    private String type;
    private String status;
    private String lifeTime;
    private String description;
}
