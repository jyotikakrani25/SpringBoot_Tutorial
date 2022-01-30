package com.buddy.tutorial.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("tests")
@RestController
public class TestController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() throws JsonProcessingException {


        return "success";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> test(@RequestBody HashMap<String, Object> body) {

        body.put("age", 25);
        return body;
    }

}
