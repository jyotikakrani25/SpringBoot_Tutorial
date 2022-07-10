package com.buddy.tutorial.service;

import com.buddy.tutorial.model.CurrencyInfo;

import java.util.List;

public interface CurrencyService {
    List<CurrencyInfo> getCurrencyInfo();

    CurrencyInfo getCurrencyData(String code);
}
