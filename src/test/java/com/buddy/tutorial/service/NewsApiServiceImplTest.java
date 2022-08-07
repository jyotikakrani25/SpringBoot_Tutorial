package com.buddy.tutorial.service;

import com.buddy.tutorial.model.ArticlesDetails;
import com.buddy.tutorial.model.CategoryEnum;
import com.buddy.tutorial.model.NewsAPIResponse;
import com.buddy.tutorial.model.SourcesDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class NewsApiServiceImplTest {

    private final String newsAPIUrl = "https://newsapi.org/v2/top-headlines?language={lang}&category={category}";

    private final String keyValue = "abcderfghijk";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NewsApiServiceImpl newsApiService;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(newsApiService, "newsAPIUrl", newsAPIUrl);
        //newsApiService.keyValue=keyValue;
        ReflectionTestUtils.setField(newsApiService, "keyValue", keyValue);
    }

    @Test
    void getnews_whenLanguageIsEN() {
        //given
        String language = "en";
        String category = "";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", keyValue);

        HttpEntity<Object> request = new HttpEntity<>(headers);

        NewsAPIResponse response = getNewsAPIResponse();
        ResponseEntity<NewsAPIResponse> reponseEntity = ResponseEntity.status(HttpStatus.OK).body(response);

        //when
        Mockito.when(restTemplate.exchange(newsAPIUrl,
                HttpMethod.GET,
                request,
                NewsAPIResponse.class,
                language, CategoryEnum.BUSINESS)).thenReturn(reponseEntity);

        newsApiService.getTopHeadlinesDetails(language, CategoryEnum.BUSINESS);
        assertThat(reponseEntity).isNotNull();
    }

    @Test
    void getnews_whenLanguageIsNull() {
        //given
        String language = null;
        String category = "";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", keyValue);

        HttpEntity<Object> request = new HttpEntity<>(headers);

        NewsAPIResponse response = getNewsAPIResponse();
        ResponseEntity<NewsAPIResponse> reponseEntity = ResponseEntity.status(HttpStatus.OK).body(response);

        //when
        Mockito.when(restTemplate.exchange(newsAPIUrl,
                HttpMethod.GET,
                request,
                NewsAPIResponse.class,
                language, CategoryEnum.BUSINESS)).thenReturn(reponseEntity);

        newsApiService.getTopHeadlinesDetails(language, CategoryEnum.BUSINESS);
        assertThat(reponseEntity).isNotNull();
    }

    private NewsAPIResponse getNewsAPIResponse() {

        NewsAPIResponse response = new NewsAPIResponse();

        ArticlesDetails articles = new ArticlesDetails();
        SourcesDetails source = new SourcesDetails();
        source.setId("123");
        source.setName("fsdfhg");

        articles.setAuthor("xyz");
        articles.setContent("gfdghsw");
        articles.setDescription("hqagshjq");
        articles.setTitle("gshqgs");
        articles.setSource(source);
        articles.setUrl("gshsqg");
        articles.setPublishedAt(new Date());
        articles.setUrlToImage("shdhq");

        List<ArticlesDetails> articlesDetailsList = new ArrayList<>();
        articlesDetailsList.add(articles);

        response.setArticles(articlesDetailsList);
        response.setStatus("200");
        response.setTotalResults(articlesDetailsList.size());

        return response;
    }
}