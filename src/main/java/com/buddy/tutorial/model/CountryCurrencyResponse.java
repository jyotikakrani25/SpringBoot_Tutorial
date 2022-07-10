package com.buddy.tutorial.model;

import lombok.Data;

import java.util.List;

@Data
public class CountryCurrencyResponse {

    private boolean error;
    private String msg;
    private List<CountryCurrencyInfo> data;

}
