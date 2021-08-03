package com.company;

import java.util.Stack;

public class Track {
    private int maxCapacity;
    private Stack<Integer> data = new Stack<>();

    /**
     * Creates track with specified maxCapacity
     * @param maxCapacity, total number of balls a track can hold before it needs to be reset
     */
    public Track(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Pushes ball onto track
     * O(1) time complexity
     * @param ball, an Integer representing ball number
     */
    public void add(Integer ball) {
        this.data.add(ball);
    }

    /**
     * Releases ball from track
     * O(1) time complexity
     * @return Integer representing ball number
     */
    public Integer releaseBall() {
        return this.data.pop();
    }

    /**
     * Checks if track currently holds its maximum number of balls
     * O(1) time complexity
     * @return bool
     */
    public boolean atMaxCapacity() {
        return this.data.size() == maxCapacity;
    }

    /**
     * Checks if track is empty
     * O(1) time complexity
     * @return bool
     */
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    /**
     * Gets current number of balls on track
     * O(1) time complexity
     * @return Integer representing time from track
     */
    public Integer getTime() {
        return this.data.size();
    }
}
