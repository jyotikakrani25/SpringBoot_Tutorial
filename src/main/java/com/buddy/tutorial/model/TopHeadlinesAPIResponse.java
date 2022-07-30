package com.buddy.tutorial.model;

import lombok.Data;

import java.util.List;

@Data
public class TopHeadlinesAPIResponse {

    private Integer records;
    private List<TopHeadlinesDetail> news;
}
