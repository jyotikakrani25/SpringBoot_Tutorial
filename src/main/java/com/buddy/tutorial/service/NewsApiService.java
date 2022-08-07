package com.buddy.tutorial.service;

import com.buddy.tutorial.model.CategoryEnum;
import com.buddy.tutorial.model.TopHeadlinesAPIResponse;

public interface NewsApiService {

    TopHeadlinesAPIResponse getTopHeadlinesDetails(String language, CategoryEnum category);
}
