package com.buddy.tutorial.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseModel {

    private String status;
    private List<CurrencyInfo> data;
}
