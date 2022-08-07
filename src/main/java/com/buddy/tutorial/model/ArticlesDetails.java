package com.buddy.tutorial.model;

import lombok.Data;

import java.util.Date;

@Data
public class ArticlesDetails {

    private SourcesDetails source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date publishedAt;
    private String content;


}
