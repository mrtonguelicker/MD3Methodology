package com.example.md3methodology;

public class ExperimentResults {

    public static final int Trials_Per_Condition = 0;

    //total time across all trials (ms)
    public static long time48 = 0;
    public static long time32 = 0;

    public static int misTaps48 = 0;
    public static int misTaps32 = 0;

    public static int runs48 = 0;
    public static int runs32 = 0;

    public static void reset()
    {
        time48 = 0;
        time32 = 0;
        misTaps48 = 0;
        misTaps32 = 0;
    }
}