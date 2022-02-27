package com.buddy.tutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping
    public int test() {
        int i = 0;
        for (int j = 0; j < 1000; j++) {
            if (j % 15 == 2)
                System.out.println("2");
            if (j % 15 == 3)
                System.out.println("3");
            if (j % 15 == 5)
                System.out.println("5");
            if (j % 15 == 7)
                System.out.println("7");
        }

        return i;
    }
}
