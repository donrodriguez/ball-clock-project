package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BallClock {

    private Track minuteTrack;
    private Track fiveMinuteTrack;
    private Track hourTrack;
    private Queue<Integer> bottomTrack;
    private String OGBottomTrack;
    private Integer ballCount;
    private Integer totalMinutes = 0;

    public BallClock() {
        this.minuteTrack = new Track(5);
        this.fiveMinuteTrack = new Track(12);
        this.hourTrack = new Track(12);
        this.bottomTrack = new LinkedList<>();
    }

    /**
     * Gets starting ball count from user
     */
    public void getInput() {

        System.out.println("Welcome to the Ball Clock!");
        Scanner scanner = new Scanner(System.in);

        boolean validInput = false;
        while(!validInput) {

            try {
                System.out.println("Please enter a number between 27-127: ");
                ballCount = scanner.nextInt();

                if(ballCount >= 27 && ballCount <= 127) {
                    validInput = true;
                } else {
                    throw new Exception();
                }

            } catch(Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Initializes ball clock. Goes until ORIGINAL, bottom track ball order matches CURRENT bottom track ball order.
     */
    public void runClock() {

        setUpBottomTrack();

        do {
            tickMinute();

        } while(!ballOrderSameAsOriginal());

        System.out.println(ballCount + " balls take " + getDays() + " days");
    }

    /**
     * Creates original bottom track from ball count
     */
    private void setUpBottomTrack() {
        for (int i = 1; i <= ballCount; i++) {
            bottomTrack.add(i);
        }
        LinkedList<Integer> originalBottomTrack = new LinkedList<>(bottomTrack);
        OGBottomTrack = originalBottomTrack.toString();
    }

    /**
     * Prints current time of ball clock as string (e.g., "03:15")
     * O(1) time complexity
     */
    private void displayTime() {
        Integer fiveMinuteTrackMultiple = 5;
        Integer minute = minuteTrack.getTime() + (fiveMinuteTrack.getTime() * fiveMinuteTrackMultiple);
        Integer hour = hourTrack.getTime();

        System.out.printf("%02d:%02d", hour, minute);
        System.out.println("\n");
    }

    /**
     * Gets total days passed
     * O(1) time complexity
     * @return int representing current number of days passed since clock initialization
     */
    private int getDays() {
        var hoursPerMinute = 1.0 / 60.0;
        var daysPerHour = 1.0 / 24.0;
        double days = totalMinutes * hoursPerMinute * daysPerHour;
        return (int) days;
    }

    /**
     * Simulates the passing of 1 minute on ball clock and the physical machinations that take place
     * O(1) time complexity
     */
    private void tickMinute() {
        Integer removedBall = bottomTrack.remove();

        minuteTrack.add(removedBall);
        if(minuteTrack.atMaxCapacity()) {
            removedBall = minuteTrack.releaseBall();
            while (!minuteTrack.isEmpty()) {
                bottomTrack.add(minuteTrack.releaseBall());
            }

            fiveMinuteTrack.add(removedBall);
            if(fiveMinuteTrack.atMaxCapacity()) {
                removedBall = fiveMinuteTrack.releaseBall();
                while (!fiveMinuteTrack.isEmpty()) {
                    bottomTrack.add(fiveMinuteTrack.releaseBall());
                }

                hourTrack.add(removedBall);
                if(hourTrack.atMaxCapacity()) {
                    removedBall = hourTrack.releaseBall();
                    while (!hourTrack.isEmpty()) {
                        bottomTrack.add(hourTrack.releaseBall());
                    }
                    bottomTrack.add(removedBall);
                }
            }
        }
        totalMinutes++;
    }

    /**
     * Checks if the bottom track has the same order of balls as when the clock first initialized
     * O(n) time complexity
     * @return bool representing if bottom track has the same order of balls as when the clock first initialized
     */
    private boolean ballOrderSameAsOriginal() {
        if(bottomTrack.size() == ballCount) {
            return OGBottomTrack.equals(bottomTrack.toString());
        } else {
            return false;
        }
    }
}