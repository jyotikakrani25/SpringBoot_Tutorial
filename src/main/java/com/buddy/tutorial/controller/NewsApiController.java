package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.CategoryEnum;
import com.buddy.tutorial.model.ResponseModel;
import com.buddy.tutorial.model.StatusCode;
import com.buddy.tutorial.model.TopHeadlinesAPIResponse;
import com.buddy.tutorial.service.NewsApiService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsApiController {

    @Autowired
    private NewsApiService newsApiService;

    @GetMapping("/news/top-headlines")
    public ResponseModel getTopHeadlines(@RequestParam(defaultValue = "en") @Schema(defaultValue = "en", allowableValues = {"en", "ar"}) final String language, @RequestParam(required = false) final CategoryEnum category) {

        TopHeadlinesAPIResponse topheadlinesdetails = newsApiService.getTopHeadlinesDetails(language, category);
        ResponseModel model = new ResponseModel();
        model.setStatus(StatusCode.STATUS_CODE_OK);
        model.setData(topheadlinesdetails);

        return model;
    }

}
