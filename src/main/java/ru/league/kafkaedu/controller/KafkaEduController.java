package ru.league.kafkaedu.controller;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.league.kafkaedu.dto.Paperless;
import ru.league.kafkaedu.kafka.PaperlessAvroProducer;
import ru.league.kafkaedu.kafka.PaperlessObjectProducer;
import ru.league.kafkaedu.kafka.PaperlessStringProducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/kafka-edu")
@RequiredArgsConstructor
public class KafkaEduController {
    private final PaperlessStringProducer producer;
    private final PaperlessObjectProducer producerObject;
    private final PaperlessAvroProducer avroProducer;

    @PostMapping("/produce")
    public ResponseEntity<String> produce(@RequestBody Paperless paperless) {
        log.info("Accept request {}", paperless);
        producer.pushMessage(paperless);
        producerObject.pushMessage(paperless);
        avroProducer.pushMessage(buildTaskResultAvro(paperless));
        return ResponseEntity.ok("Done!");
    }

    @SneakyThrows
    public GenericRecord buildTaskResultAvro(Paperless request) {
        GenericRecord avroRecord = getGenericRecord();
        avroRecord.put("instanceId", request.getInstanceId());
        avroRecord.put("siebel_id", request.getSiebelId());

        Schema schemaTask = avroRecord.getSchema().getField("task").schema();
        GenericRecord task = new GenericData.Record(schemaTask);
        task.put("id", request.getTask().getId());
        task.put("requestId", "1234");
        task.put("type", request.getTask().getType());
        task.put("taskFactId", request.getTask().getType());
        task.put("status", "SUCCESS");
        task.put("lifeTime", request.getTask().getLifeTime());
        task.put("description", request.getTask().getDescription());
        avroRecord.put("task", task);

        Schema schemaDocuments = avroRecord.getSchema().getField("documents").schema().getElementType();
        List<GenericRecord> documents = new ArrayList();
        GenericRecord doc = new GenericData.Record(schemaDocuments);
        doc.put("id", "1");
        doc.put("documentType", "pdf");
        documents.add(doc);
        avroRecord.put("documents", documents);
        return avroRecord;
    }

    @SneakyThrows
    private static GenericRecord getGenericRecord() {
        Schema schema = getSchema();
        return new GenericData.Record(schema);
    }

    private static Schema getSchema() throws IOException {
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(new ClassPathResource("avro/paperless.avsc").getInputStream());
        return schema;
    }
}
