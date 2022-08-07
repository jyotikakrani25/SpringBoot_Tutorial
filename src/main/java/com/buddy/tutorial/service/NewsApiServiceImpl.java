package com.buddy.tutorial.service;

import com.buddy.tutorial.model.ArticlesDetails;
import com.buddy.tutorial.model.CategoryEnum;
import com.buddy.tutorial.model.NewsAPIResponse;
import com.buddy.tutorial.model.TopHeadlinesAPIResponse;
import com.buddy.tutorial.model.TopHeadlinesDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsApiServiceImpl implements NewsApiService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${newsapi.url}")
    private String newsAPIUrl;

    @Value("${newsapi.key.value}")
    private String keyValue;

    @Override
    public TopHeadlinesAPIResponse getTopHeadlinesDetails(final String language, final CategoryEnum category) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", keyValue);
        HttpEntity<Object> request = new HttpEntity<>(headers);

        ResponseEntity<NewsAPIResponse> response = restTemplate.exchange(newsAPIUrl,
                HttpMethod.GET,
                request,
                NewsAPIResponse.class,
                language,
                category
        );

        NewsAPIResponse newsAPIResponse = response.getBody();
        if (newsAPIResponse == null) {
            throw new RuntimeException("No data found from News API");
        }

        List<ArticlesDetails> articlesDetails = newsAPIResponse.getArticles();

        List<TopHeadlinesDetail> topHeadlinesDetailList = convertDTO(articlesDetails);

        TopHeadlinesAPIResponse headlinesAPIResponse = new TopHeadlinesAPIResponse();
        headlinesAPIResponse.setNews(topHeadlinesDetailList);
        headlinesAPIResponse.setRecords(newsAPIResponse.getTotalResults());

        return headlinesAPIResponse;

    }

    private List<TopHeadlinesDetail> convertDTO(final List<ArticlesDetails> articlesDetails) {
        List<TopHeadlinesDetail> headlinesList = new ArrayList<>();


        for (ArticlesDetails articlesDetail : articlesDetails) {
            TopHeadlinesDetail headlines = new TopHeadlinesDetail();
            headlines.setSourceId(articlesDetail.getSource().getId());
            headlines.setSourceName(articlesDetail.getSource().getName());
            headlines.setAuthor(articlesDetail.getAuthor());
            headlines.setDescription(articlesDetail.getDescription());
            headlines.setTitle(articlesDetail.getTitle());
            headlines.setLink(articlesDetail.getUrl());
            headlines.setPublishedAt(formatDate(articlesDetail.getPublishedAt()));
            headlinesList.add(headlines);
        }
        return headlinesList;
    }

    private String formatDate(final Date date) {

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        return dateformat.format(date);
    }
}
