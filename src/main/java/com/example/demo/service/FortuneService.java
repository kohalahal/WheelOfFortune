package com.example.demo.service;

import com.example.demo.domain.Fortune;
import com.example.demo.repository.FortuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FortuneService {
    private final FortuneRepository fortuneRepository;
    private List<Fortune> fortuneList;

    @Autowired
    public FortuneService(FortuneRepository fortuneRepository) {
        this.fortuneRepository = fortuneRepository;
        fortuneList = fortuneRepository.findAll();
    }

    public List<Fortune> getAll() {
        return fortuneList;
    }

    public Optional<Fortune> getRandom() {

        return Optional.ofNullable(null);
    }
}