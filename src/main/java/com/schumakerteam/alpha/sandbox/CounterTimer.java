package com.schumakerteam.alpha.sandbox;

import java.util.Timer;
import java.util.TimerTask;

public class CounterTimer {
    public static void main(String[] args) {
        Timer timer = new Timer(true);

        TimerTask task = new TimerTask() {
            private int k = 0;

            public void run() {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("executes function for every 1 Second" + k);
                k++;
            }
        };
        //timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec
        //timer.scheduleAtFixedRate(task, 5000, 1000); //1000ms = 1sec
        timer.schedule(task,  500); //1000ms = 1sec
        for(int i =0; i < 100000; i++) {
            System.out.print(".");
        }


    }
}
