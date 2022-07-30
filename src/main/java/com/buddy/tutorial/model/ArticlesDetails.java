package com.buddy.tutorial.model;

import lombok.Data;

@Data
public class ArticlesDetails {

    private SourcesDetails source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;


}
