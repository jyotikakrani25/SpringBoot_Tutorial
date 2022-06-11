package com.buddy.tutorial.model;

import lombok.Data;

import java.util.List;

@Data
public class CountryCurrencyResponse {

    private final String error;
    private final String msg;
    private final List<CountryCurrencyInfo> data;
}
