package com.buddy.tutorial.model;

import lombok.Data;

@Data
public class TopHeadlinesDetail {

    private String sourceId;
    private String sourceName;
    private String author;
    private String title;
    private String description;
    private String link;
    private String publishedAt;
}
