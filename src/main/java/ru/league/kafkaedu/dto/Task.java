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
public class Task {
    @JsonProperty("id")
    private String id;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("lifeTime")
    private String lifeTime;
    @JsonProperty("description")
    private String description;
}
