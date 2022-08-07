package com.buddy.tutorial.service;

import com.buddy.tutorial.model.CategoryEnum;
import com.buddy.tutorial.model.TopHeadlinesAPIResponse;

public interface NewsApiService {

    TopHeadlinesAPIResponse getTopheadlinesDetails(String language, CategoryEnum category);
}
