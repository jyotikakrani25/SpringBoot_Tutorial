package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.CurrencyInfo;
import com.buddy.tutorial.model.ResponseModel;
import com.buddy.tutorial.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CurrencyController {

    private static final Integer STATUS_CODE_OK = 200;
    @Autowired
    private CurrencyService ccService;


    @GetMapping
    public ResponseModel getAllCurrency() {

        ResponseModel responseModel = new ResponseModel();
        List<CurrencyInfo> currencyInfoList = ccService.getCurrencyInfo();
        responseModel.setStatus(STATUS_CODE_OK);
        responseModel.setData(currencyInfoList);
        return responseModel;
    }

    @GetMapping("/{code}")
    public ResponseModel getCurrency(@PathVariable final String code) {
        CurrencyInfo countrydata = ccService.getCurrencyData(code);
        ResponseModel model = new ResponseModel();
        model.setStatus(STATUS_CODE_OK);
        model.setData(countrydata);

        return model;
    }
}
