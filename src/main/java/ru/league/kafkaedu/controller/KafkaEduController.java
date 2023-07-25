package ru.league.kafkaedu.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.league.kafkaedu.dto.Paperless;
import ru.league.kafkaedu.kafka.PaperlessProducer;

@Slf4j
@RestController
@RequestMapping("/kafka-edu")
@RequiredArgsConstructor
public class KafkaEduController {
    private final PaperlessProducer producer;

    @PostMapping("/produce")
    public ResponseEntity<String> produce(@RequestBody Paperless paperless) {
        log.info("Accept request {}", paperless);
        producer.pushMessage(paperless);
        return ResponseEntity.ok("Done!");
    }
}
