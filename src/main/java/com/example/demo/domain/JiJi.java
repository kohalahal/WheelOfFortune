package com.example.demo.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum JiJi {
    IN("tiger"), MYO("rabbit"), JIN("dragon"), SA("snake"),
    O("horse"), MI("sheep"), SHIN("monkey"), YU("chicken"),
    SOOL("dog"), HAE("pig"), JA("mouse"), CHOOK("cow");

    private final String symbol;

    //랜덤 뽑기를 위해서 배열 캐싱(values()는 할 때마다 배열을 복사)
    private static final JiJi[] JIJIS = values();
    private static final Random RANDOM = new Random();
    private static final int lastIndex = 11;

    JiJi(String symbol) {
        this.symbol = symbol;
    }

    public static JiJi getJiJi() {
        //밑에거 대신에 캐싱 배열 활용
        //return values()[RANDOM.nextInt(lastIndex)];
        return JIJIS[RANDOM.nextInt(lastIndex)];
    }

    public int number() {
        //지지에 해당하는 1~12 숫자
        return ordinal() + 1;
    }

}
