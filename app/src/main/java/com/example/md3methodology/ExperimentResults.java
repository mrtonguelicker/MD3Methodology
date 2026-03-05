package com.example.md3methodology;

import java.util.Arrays;

public class ExperimentResults {

    public static final int Trials_Per_Condition = 0;
    public static long[] totalTime;
    public static int[] runs;
    public static int[] misClicks;

    public static void init(int numTargets){
        totalTime = new long[numTargets];
        runs = new int[numTargets];
        misClicks = new int[numTargets];
    }

    public static void reset() {
        if (totalTime == null) return;
        Arrays.fill(totalTime, 0);
        Arrays.fill(runs, 0);
        Arrays.fill(misClicks, 0);
    }








    // ### DEPRECATED CODE BLOCK

    /*
    //total time across all trials (ms)
    public static long time48 = 0;
    public static long time32 = 0;

    public static int misClicks48 = 0;
    public static int misClicks32 = 0;

    public static int runs48 = 0;
    public static int runs32 = 0;

    public static void reset()
    {
        time48 = 0;
        time32 = 0;
        misClicks48 = 0;
        misClicks32 = 0;
    }*/
}