package it.eg.sloth.spring.context.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Document {
    Integer idDocument;
    String name;
    LocalDateTime documentDate;
    Double cost;
    String flagActive;
}