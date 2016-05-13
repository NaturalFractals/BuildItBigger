package com.example;

import java.util.Random;

public class Joker {
    public static final String[] JOKES = {"Joke"};

    public String getJoke() {
        Random random = new Random();
        return JOKES[random.nextInt(JOKES.length)];
    }


}
