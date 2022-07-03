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

    @Autowired
    private CurrencyService ccService;

    @GetMapping
    public ResponseModel getAllCurrency() {

        ResponseModel responseModel = new ResponseModel();
        List<CurrencyInfo> currencyInfoList = ccService.getCurrencyInfo();
        responseModel.setStatus(200);
        responseModel.setData(currencyInfoList);
        return responseModel;
    }

    @GetMapping("/{code}")
    public ResponseModel getCurrency(@PathVariable final String code) {
        CurrencyInfo countrydata = ccService.getCurrencyData(code);
        ResponseModel model = new ResponseModel();
        model.setStatus(200);
        model.setData(countrydata);

        return model;
    }
}
