package com.buddy.tutorial.service;

import com.buddy.tutorial.model.CountryCurrencyInfo;
import com.buddy.tutorial.model.CountryCurrencyResponse;
import com.buddy.tutorial.model.CurrencyInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    private final String url = "https://countriesnow.space/api/v0.1/countries/currency";
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(currencyService, "url", "https://countriesnow.space/api/v0.1/countries/currency");
    }

    @Test
    public void getCurrencyInfo_Success() {

        CountryCurrencyInfo countryCurrencyInfo = new CountryCurrencyInfo();
        countryCurrencyInfo.setCurrency("INR");
        countryCurrencyInfo.setName("India");
        countryCurrencyInfo.setIso2("IN");
        countryCurrencyInfo.setIso3("IN");

        List<CountryCurrencyInfo> data = new ArrayList<>();
        data.add(countryCurrencyInfo);

        CountryCurrencyResponse response = new CountryCurrencyResponse();
        response.setData(data);

        CurrencyInfo currencyInfo = new CurrencyInfo();
        currencyInfo.setCurrency("INR");
        currencyInfo.setCode("IN");
        currencyInfo.setCountry("India");


        Mockito.when(restTemplate.getForObject(url, CountryCurrencyResponse.class)).thenReturn(response);
        List<CurrencyInfo> result = currencyService.getCurrencyInfo();

        assertNotNull(result);
    }

    @Test
    public void getCurrencyData_Success() {

        CountryCurrencyInfo countryCurrencyInfo = new CountryCurrencyInfo();
        countryCurrencyInfo.setCurrency("INR");
        countryCurrencyInfo.setName("India");
        countryCurrencyInfo.setIso3("IN");
        countryCurrencyInfo.setIso2("IN");


        List<CountryCurrencyInfo> data = new ArrayList<>();
        data.add(countryCurrencyInfo);

        CountryCurrencyResponse response = new CountryCurrencyResponse();
        response.setData(data);

        Mockito.when(restTemplate.getForObject(url, CountryCurrencyResponse.class)).thenReturn(response);

        CurrencyInfo result = currencyService.getCurrencyData("IN");

        assertNotNull(result);

    }

    @Test
    public void getCurrencyData_Failure() {

        assertThrows(RuntimeException.class, () -> currencyService.getCurrencyData("XYZ"), "No record found");

    }

}