package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BallClock ballClock = new BallClock();
        ballClock.getInput();

        long startTime = System.currentTimeMillis();
        ballClock.runClock();
        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);  //divide by 1000
        System.out.println("duration: " + duration + " milliseconds (" + (duration / 1000.0) + " seconds)");

        // TODO: optimize tickMinute() method with a different algorithm. I read something about LCM method that
        // figures out the different rotations of a ball, which gives insight into the bottom track arrangement
        // TODO; Perhaps, more optimization can happen in string comparisons (the ballOrderSameAsOriginal() method) to see if
    }
}
