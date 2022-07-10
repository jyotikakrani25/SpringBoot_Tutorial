package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.CurrencyInfo;
import com.buddy.tutorial.model.ResponseModel;
import com.buddy.tutorial.service.CurrencyService;
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

class CurrencyControllerTest {

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllCurrency_Success() {

        CurrencyInfo currencyInfo = new CurrencyInfo();
        currencyInfo.setCurrency("INR");
        currencyInfo.setCountry("India");
        currencyInfo.setCode("IN");

        List<CurrencyInfo> currencyInfoList = new ArrayList<>();
        currencyInfoList.add(currencyInfo);

        Mockito.when(currencyService.getCurrencyInfo()).thenReturn(currencyInfoList);

        ResponseModel response = controller.getAllCurrency();


        assertNotNull(response);
        assertEquals(response.getData(), currencyInfoList);
    }

    @Test
    void getCurrency_Success() {

        CurrencyInfo currencyInfo = new CurrencyInfo();
        currencyInfo.setCurrency("INR");
        currencyInfo.setCountry("India");
        currencyInfo.setCode("IN");

        Mockito.when(currencyService.getCurrencyData("IN")).thenReturn(currencyInfo);

        ResponseModel response = controller.getCurrency("IN");


        assertNotNull(response);
        assertEquals(response.getData(), currencyInfo);
    }

}