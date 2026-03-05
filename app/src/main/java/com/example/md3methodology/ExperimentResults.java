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
}