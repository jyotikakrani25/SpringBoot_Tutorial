package com.buddy.tutorial.service;

import com.buddy.tutorial.model.CurrencyInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService {
    List<CurrencyInfo> getCurrencyInfo();

    CurrencyInfo getCurrencyData(final String code);
}
