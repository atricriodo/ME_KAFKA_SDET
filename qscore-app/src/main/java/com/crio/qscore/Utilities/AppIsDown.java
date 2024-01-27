package com.crio.qscore.Utilities;

public class AppIsDown {
    private static final long DELAY_TIME_MS = 10000; // 10 seconds before downtime starts
    private static long firstRequestTime = 0;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void checkIfDown() {
        long currentTime = System.currentTimeMillis();
    
        // Time since the epoch in minutes
        long timeInMinutes = currentTime / 60000; 
        // Time since the epoch in seconds within the current minute
        long timeInSecondsInCurrentMinute = (currentTime / 1000) % 60; 
    
        // App goes down for 10 seconds every minute
        if (timeInSecondsInCurrentMinute >= 0 && timeInSecondsInCurrentMinute < 10) {
            System.out.println(ANSI_RED + "DUE TO HIGH TRAFFIC, QScore is down!!!" + ANSI_RESET);
            throw new AppDownException("Service temporarily unavailable due to high traffic.");
        }
    }    
}