package com.example.demo.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum CheonGan {
    GAP("tree", 3), EUL("grass", 8),
    BYEONG("sun", 7), JEONG("candle", 2),
    MOO("land", 5), GI("soil", 10),
    GYEONG("rock", 9), SHIN("jewel", 4),
    IM("ocean", 1), GYE("rain", 6);

    private final String symbol;
    private final int number;

    //랜덤 뽑기를 위해서 배열 캐싱(values()는 할 때마다 배열을 복사)
    private static final CheonGan[] CHEONGANS = values();
    private static final Random RANDOM = new Random();
    private static final int lastIndex = 9;

    CheonGan(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static CheonGan getCheonGan() {
        //밑에거 대신에 캐싱 배열 활용
        //return values()[RANDOM.nextInt(lastIndex)];
        return CHEONGANS[RANDOM.nextInt(lastIndex)];
    }

    public int number() {
        return number;
    }

}
