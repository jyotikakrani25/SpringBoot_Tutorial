package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.CategoryEnum;
import com.buddy.tutorial.model.ResponseModel;
import com.buddy.tutorial.model.TopHeadlinesAPIResponse;
import com.buddy.tutorial.model.TopHeadlinesDetail;
import com.buddy.tutorial.service.NewsApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NewsApiControllerTest {

    @Mock
    private NewsApiService newsApiService;

    @InjectMocks
    private NewsApiController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getTopHeadlines_Success() {

        String language = "ar";
        TopHeadlinesAPIResponse apiresponse = getTopHeadlinesResponse();

        Mockito.when(newsApiService.getTopheadlinesDetails(language, CategoryEnum.business)).thenReturn(apiresponse);

        ResponseModel response = controller.getTopHeadlines(language, CategoryEnum.business);

        assertNotNull(response);
        assertEquals(response.getData(), apiresponse);
    }

    private TopHeadlinesAPIResponse getTopHeadlinesResponse() {

        TopHeadlinesAPIResponse headlinesAPIResponse = new TopHeadlinesAPIResponse();
        TopHeadlinesDetail headlinesDetail = new TopHeadlinesDetail();
        headlinesDetail.setAuthor("abc");
        headlinesDetail.setLink("abc");
        headlinesDetail.setTitle("abc");
        headlinesDetail.setSourceId("abc");
        headlinesDetail.setDescription("abc");
        headlinesDetail.setPublishedAt("abc");
        headlinesDetail.setSourceName("abc");

        List<TopHeadlinesDetail> topHeadlinesDetailList = new ArrayList<>();
        topHeadlinesDetailList.add(headlinesDetail);

        headlinesAPIResponse.setNews(topHeadlinesDetailList);
        headlinesAPIResponse.setRecords(48);

        return headlinesAPIResponse;
    }

}