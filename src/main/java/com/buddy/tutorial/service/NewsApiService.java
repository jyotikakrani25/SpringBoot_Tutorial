package com.buddy.tutorial.service;

import com.buddy.tutorial.model.TopHeadlinesDetail;

import java.util.List;

public interface NewsApiService {

    List<TopHeadlinesDetail> getNews(String language, String category);
}
