package com.buddy.tutorial.service;

import com.buddy.tutorial.model.CountryCurrencyInfo;
import com.buddy.tutorial.model.CountryCurrencyResponse;
import com.buddy.tutorial.model.CurrencyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${countriesnow.currency.url}")
    private String url;

    @Override
    public List<CurrencyInfo> getCurrencyInfo() {
        CountryCurrencyResponse response = restTemplate.getForObject(url, CountryCurrencyResponse.class);
        if (null == response) {
            throw new RuntimeException("No Response from Countriesnow API");
        }
        log.debug("Response from countriesnow {}", response);

        if (response.isError()) {
            throw new RuntimeException("There is some error at Countries Now API.We can't proceed");
        }
        List<CountryCurrencyInfo> currencyInfoList = response.getData();
        return convertDTO(currencyInfoList);
    }

    @Override
    public CurrencyInfo getCurrencyData(final String code) {

        CountryCurrencyResponse response = restTemplate.getForObject(url, CountryCurrencyResponse.class);
        if (null == response) {
            throw new RuntimeException("No Response from Countriesnow API");
        }

        if (response.isError()) {
            throw new RuntimeException("There is some error at Countries Now API.We can't proceed");
        }
        List<CountryCurrencyInfo> data = response.getData();
        CountryCurrencyInfo countryCurrencyInfo = filter(data, code);
        return convertDTO(countryCurrencyInfo);
    }


    private CurrencyInfo convertDTO(final CountryCurrencyInfo countryCurrencyInfo) {

        CurrencyInfo output = new CurrencyInfo();
        log.info("Translating the country {}", countryCurrencyInfo.getName());
        output.setCurrency(countryCurrencyInfo.getCurrency());
        output.setCountry(countryCurrencyInfo.getName());
        output.setCode(countryCurrencyInfo.getIso2());

        return output;
    }

    private List<CurrencyInfo> convertDTO(final List<CountryCurrencyInfo> inputList) {
        List<CurrencyInfo> outputList = new ArrayList<>();
        for (CountryCurrencyInfo input : inputList) {
            outputList.add(convertDTO(input));
        }
        return outputList;
    }

    private CountryCurrencyInfo filter(final List<CountryCurrencyInfo> data, final String code) {

        for (CountryCurrencyInfo countryCurrencyInfo : data) {
            if (code.equalsIgnoreCase(countryCurrencyInfo.getCurrency()) || code.equalsIgnoreCase(countryCurrencyInfo.getIso2())) {
                return countryCurrencyInfo;
            }
        }
        throw new RuntimeException("No record found");
    }
}
