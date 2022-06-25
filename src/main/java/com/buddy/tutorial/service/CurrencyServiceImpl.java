package com.buddy.tutorial.service;

import com.buddy.tutorial.model.CountryCurrencyInfo;
import com.buddy.tutorial.model.CountryCurrencyResponse;
import com.buddy.tutorial.model.CurrencyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {


    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${countriesnow.currency.url}")
    private String url;

    @Override
    public List<CurrencyInfo> getCurrencyInfo() {
        CountryCurrencyResponse response = restTemplate.getForObject(url, CountryCurrencyResponse.class);
        log.debug("Response from countriesnow {}", response);
        List<CountryCurrencyInfo> currencyInfoList = null;
        if (response != null) {
            currencyInfoList = response.getData();
        }
        if (currencyInfoList != null) {
            return convertDTO(currencyInfoList);
        }
        throw new RuntimeException("no response");
    }

    @Override
    public CurrencyInfo getCurrencyData(String code) {

        CountryCurrencyResponse response = restTemplate.getForObject(url, CountryCurrencyResponse.class);
        List<CountryCurrencyInfo> data = null;
        if (response != null) {
            data = response.getData();
        }
        CountryCurrencyInfo countryCurrencyInfo = filter(data, code);
        return convertDTO(countryCurrencyInfo);
    }

    private CurrencyInfo convertDTO(CountryCurrencyInfo countryCurrencyInfo) {

        CurrencyInfo output = new CurrencyInfo();
        log.info("Translating the country {}", countryCurrencyInfo.getName());
        output.setCurrency(countryCurrencyInfo.getCurrency());
        output.setCountry(countryCurrencyInfo.getName());
        output.setCode(countryCurrencyInfo.getIso2());

        return output;
    }

    private CountryCurrencyInfo filter(List<CountryCurrencyInfo> data, String code) {

        for (CountryCurrencyInfo countryCurrencyInfo : data) {
            if (countryCurrencyInfo.getCurrency().equalsIgnoreCase(code) || countryCurrencyInfo.getIso2().equalsIgnoreCase(code)) {
                return countryCurrencyInfo;
            }
        }
        throw new RuntimeException("No record found");
    }

    private List<CurrencyInfo> convertDTO(List<CountryCurrencyInfo> inputList) {
        List<CurrencyInfo> outputList = new ArrayList<>();
        for (CountryCurrencyInfo input : inputList) {
            CurrencyInfo output = new CurrencyInfo();
            log.info("Translating the country {}", input.getName());
            output.setCurrency(input.getCurrency());
            output.setCountry(input.getName());
            output.setCode(input.getIso2());
            outputList.add(output);
        }
        return outputList;
    }
}
