package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.CurrencyInfo;
import com.buddy.tutorial.model.ResponseModel;
import com.buddy.tutorial.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    public ResponseModel responseModel;
    @Autowired
    public CurrencyService ccService;

    @GetMapping
    public ResponseModel getAllCurrency() {

        List<CurrencyInfo> currencyInfoList = ccService.getCurrencyInfo();
        responseModel.setStatus("200");
        responseModel.setData(currencyInfoList);
        return responseModel;
    }


}
