package com.example;

import java.util.Random;

public class Joker {
    public static final String[] JOKES = {"Joke 1", "Joke 2", "Joke 3"};

    public String getJoke() {
        Random random = new Random();
        return JOKES[random.nextInt(JOKES.length)];
    }


}
