package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.ResponseModel;
import com.buddy.tutorial.model.TopHeadlinesAPIResponse;
import com.buddy.tutorial.model.TopHeadlinesDetail;
import com.buddy.tutorial.service.NewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsApiController {


    private static final Integer STATUS_CODE_OK = 200;
    @Autowired
    private NewsApiService newsApiService;

    @GetMapping("top-headlines")
    public ResponseModel getTopHeadlines(@RequestParam(defaultValue = "en") String language, @RequestParam(required = false) String category) {

        ResponseModel model = new ResponseModel();

        List<TopHeadlinesDetail> topheadlinesdetails = newsApiService.getNews(language, category);

        TopHeadlinesAPIResponse response = new TopHeadlinesAPIResponse();
        response.setRecords(topheadlinesdetails.size());
        response.setNews(topheadlinesdetails);

        model.setStatus(STATUS_CODE_OK);
        model.setData(response);

        return model;
    }

}
