package com.buddy.tutorial.controller;

import com.buddy.tutorial.model.CurrencyInfo;
import com.buddy.tutorial.model.ResponseModel;
import com.buddy.tutorial.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService ccService;

    @GetMapping
    public ResponseModel getAllCurrency() {

        ResponseModel responseModel = new ResponseModel();
        List<CurrencyInfo> currencyInfoList = ccService.getCurrencyInfo();
        responseModel.setStatus("200");
        responseModel.setData(currencyInfoList);
        return responseModel;
    }

    @GetMapping("/currencycode")
    public ResponseModel getCurrency(@RequestParam final String code) {

        ResponseModel model = new ResponseModel();
        CurrencyInfo countrydata = ccService.getCurrencyData(code);

        model.setStatus("200");
        model.setData(countrydata);

        return model;
    }
}
