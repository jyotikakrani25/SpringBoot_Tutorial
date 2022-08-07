package com.buddy.tutorial.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TopHeadlinesDetail {

    private String sourceId;
    private String sourceName;
    private String author;
    private String title;
    private String description;
    private String link;
    private LocalDate publishedAt;
}
