package com.buddy.tutorial.service;

import com.buddy.tutorial.model.CountryCurrencyInfo;
import com.buddy.tutorial.model.CountryCurrencyResponse;
import com.buddy.tutorial.model.CurrencyInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService {


    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${countriesnow.currency.url}")
    private String url;

    @Override
    public List<CurrencyInfo> getCurrencyInfo() {
        CountryCurrencyResponse response = restTemplate.getForObject(url, CountryCurrencyResponse.class);

        List<CountryCurrencyInfo> currencyInfoList = response.getData();
        List<CurrencyInfo> currencyList = convertDTO(currencyInfoList);
        return currencyList;
    }

    private List<CurrencyInfo> convertDTO(List<CountryCurrencyInfo> currencyInfoList) {

        CurrencyInfo currencyInfo = new CurrencyInfo();

        return null;
    }
}
