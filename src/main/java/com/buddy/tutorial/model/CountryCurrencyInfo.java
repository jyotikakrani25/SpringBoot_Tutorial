package com.buddy.tutorial.model;

import lombok.Data;

@Data
public class CountryCurrencyInfo {

    private final String name;
    private final String currency;
    private final String iso2;
    private final String iso3;
}
