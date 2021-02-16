package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController<FortuneRepository> {
    private final FortuneRepository fortuneRepository;

    public HomeController(FortuneRepository fortuneRepository) {
        this.fortuneRepository = fortuneRepository;
    }
}
