package com.buddy.tutorial.model;

import lombok.Data;

import java.util.List;

@Data
public class NewsAPIResponse {

    private String status;
    private Integer totalResults;
    private List<ArticlesDetails> articles;

}
