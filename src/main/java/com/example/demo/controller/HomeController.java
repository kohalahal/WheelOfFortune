package com.example.demo.controller;

import com.example.demo.domain.CheonGan;
import com.example.demo.domain.Fortune;
import com.example.demo.domain.JiJi;
import com.example.demo.exception.FortuneNotFoundException;
import com.example.demo.exception.PersistenceException;
import com.example.demo.service.FortuneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api("메인 컨트롤러")
@RestController
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final FortuneService fortuneService;

    //뽑기용 변수
    private CheonGan cheonGan;
    private JiJi jiJi;

    @Autowired
    public HomeController(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    //랜덤운세
    @GetMapping("/fortunes/lucky")
    Fortune getFortune() throws FortuneNotFoundException {
        return fortuneService.luckyDraw(getLuckyNumber());
    }

    //id를 치고 들어오면 또 다시 랜덤 페이지를 보여주고싶은데
    @GetMapping("/fortunes/{id}")
    void getFortune(HttpServletResponse response) throws IOException {
        response.sendRedirect("/fortunes/lucky");
    }

    //나름 운세 프로그램인데 random.nextint()로 하면 너무 성의 없으니까 럭키넘버 부여
    public int getLuckyNumber() {
        //리퀘스트에 천간과 지지 랜덤 할당
        cheonGan = CheonGan.getCheonGan();
        jiJi = JiJi.getJiJi();
        //천간(10) * 지지(12) / 2 를 하면 최대 60의 숫자가 나온다
        return cheonGan.number()*jiJi.number()/2;
    }

    @GetMapping("/fortunes")
    List<Fortune> getAllFortunes() throws FortuneNotFoundException {
        return fortuneService.getAll();
    }

    @PostMapping("/fortunes")
    public Fortune postFortune(@RequestBody Fortune fortune) {
        return fortuneService.saveFortune(fortune);
    }

    @PutMapping("/fortunes/{id}")
    public Fortune putFortune(@RequestBody Fortune fortune, @RequestParam long id) throws PersistenceException {
        return fortuneService.editFortune(id, fortune);
    }

    @DeleteMapping("/fortunes/{id}")
    public void deleteFortune(@RequestParam long id) throws PersistenceException {
        fortuneService.deleteFortune(id);
    }

}
