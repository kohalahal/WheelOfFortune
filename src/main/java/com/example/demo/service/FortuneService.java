package com.example.demo.service;

import com.example.demo.domain.Fortune;
import com.example.demo.exception.FortuneNotFoundException;
import com.example.demo.exception.PersistenceException;
import com.example.demo.repository.FortuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FortuneService {
    private final FortuneRepository fortuneRepository;
    //포춘 리스트를 캐시해두기(거기서 운세 뽑음)
    //final로 하면 포춘등록수정삭제를 반영못하니까 그냥으로 함
    private List<Fortune> fortuneList;

    @Autowired
    public FortuneService(FortuneRepository fortuneRepository) {
        this.fortuneRepository = fortuneRepository;
        setFortuneList();
    }

    public Fortune luckyDraw(int luckyNumber) throws FortuneNotFoundException {
        //리스트에 운세가 없으면 에러
        if(fortuneList.isEmpty()) {
            throw new FortuneNotFoundException();
        }
        //아니면 숫자 조작해서 픽해서 리턴
        if(luckyNumber<0) luckyNumber *= -1;
        while(luckyNumber >= fortuneList.size()) {
            int a = luckyNumber%10;
            luckyNumber /= 10;
            luckyNumber += a;
        }
        return fortuneList.get(luckyNumber);
    }

    public Fortune getFortune(long id) throws FortuneNotFoundException {
        //없으면 에러
        return fortuneRepository.findById(id).orElseThrow(() -> new FortuneNotFoundException());
    }

    public Fortune saveFortune(Fortune fortune) {
        fortuneRepository.save(fortune);
        //포춘 리스트 캐시 업데이트
        setFortuneList();
        return fortune;
    }

    public Fortune editFortune(Long id, Fortune fortune) throws PersistenceException {
        //없는 운세 수정하려고 하면 에러
        try {
            getFortune(id);

            saveFortune(fortune);
            //포춘 리스트 캐시 업데이트
            setFortuneList();
            return fortune;
        } catch(FortuneNotFoundException e) {
            throw new PersistenceException();
        }
    }

    public Fortune editFortune(Fortune fortune) throws PersistenceException {
        //없는 운세 수정하려고 하면 에러
        try {
            getFortune(fortune.getId());
            saveFortune(fortune);
            //포춘 리스트 캐시 업데이트
            setFortuneList();
            return fortune;
        } catch(FortuneNotFoundException e) {
            throw new PersistenceException();
        }
    }

    public void deleteFortune(long id) throws PersistenceException {
        //없는 운세 지우려고 하면 에러
        try {
            Fortune fortune = getFortune(id);
            fortuneRepository.delete(fortune);
            //포춘 리스트 캐시 업데이트
            setFortuneList();
        } catch(FortuneNotFoundException e) {
            throw new PersistenceException();
        }
    }

    private void setFortuneList() {
        //불변으로 해놓기
        fortuneList = Collections.unmodifiableList(fortuneRepository.findAll());
    }

    public List<Fortune> getAll() {
        //불변이니까 그냥 줌
        return fortuneList;
    }
}
