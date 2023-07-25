package ru.league.kafkaedu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties
public class TaskDto {

    private String id;

    private String requestId;

    private String type;

    private String status;

    private String lifeTime;

    private String description;
}
