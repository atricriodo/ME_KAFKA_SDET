package com.crio.qscore.Utilities;

public class NonKafkaWriter {
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void write(String json) throws InterruptedException {
        AppIsDown.checkIfDown();
        Thread.sleep(2000);
        System.out.println(ANSI_YELLOW + "SENT OUT NOTIFICATION WITHOUT KAFKA: " + json + ANSI_RESET);
    }
}