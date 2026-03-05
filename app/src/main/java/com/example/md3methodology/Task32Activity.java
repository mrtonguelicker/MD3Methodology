package com.example.md3methodology;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

import org.w3c.dom.Text;

import java.util.Random;

public class Task32Activity extends AppCompatActivity {

    // CHANGE THIS TO INCREASE OR DECREASE THE NUMBER OF TRIALS
    public static final int Trials_Per_Condition = 15;
    private final Random rng = new Random();
    private FrameLayout root32;

    private long startTime;
    private long totalTime = 0;
    private int trial = 0;


    private boolean buttonDone = false;
    private boolean toggleDone = false;
    private boolean optionDone = false;
    private boolean resetting = false;

    private TextView trialCounter;
    private Button mainButton;
    private SwitchCompat toggleSwitch;
    private RadioButton option;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task32);

        root32 = findViewById(R.id.taskRoot32);
        mainButton = findViewById(R.id.mainButton32);
        toggleSwitch = findViewById(R.id.toggle32);
        option = findViewById(R.id.option32);
        trialCounter = findViewById(R.id.trialCounter);

        startTime = SystemClock.elapsedRealtime();

        mainButton.setOnClickListener(v -> {
            buttonDone = true;
            checkCompletion();
        });

        toggleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (resetting) {
                return;
            }
            if (isChecked) {
                toggleDone = true;
                checkCompletion();
            }

        });

        option.setOnClickListener(v -> {
            optionDone = true;
            checkCompletion();
        });

        root32.post(this::startTrial);
    }

    private void startTrial() {
        trialCounter.setText("32dp Targets - " + (trial+1) + "/" + Trials_Per_Condition);

        //reset all metrics
        buttonDone = false;
        toggleDone = false;
        optionDone = false;

        resetting = true;
        toggleSwitch.setChecked(false);
        option.setChecked(false);
        resetting = false;

        randomizePositions();
        startTime = SystemClock.elapsedRealtime();
    }
    private void checkCompletion() {
        if (buttonDone && toggleDone && optionDone) {

            long trialTime = SystemClock.elapsedRealtime() - startTime;
            totalTime += trialTime;
            trial++;
            ExperimentResults.runs[0] = trial;

            if(trial < Trials_Per_Condition) {
                startTrial();
            }
            else {

                ExperimentResults.totalTime[0] = totalTime;

                startActivity(new Intent(this, ResultActivity.class));
                finish();
            }
        }
    }

    private void randomizePositions() {
        int w = root32.getWidth();
        int h = root32.getHeight();
        int pad = 50;

        placeViewRandom(mainButton, w, h, pad);
        placeViewRandom(toggleSwitch, w, h, pad);
        placeViewRandom(option, w, h, pad);
    }

    private void placeViewRandom(View v, int parentW, int parentH, int pad){
        int vw = v.getWidth();
        int vh = v.getHeight();

        float maxX = Math.max(pad, parentW - vw - pad);

        // ### deprecated, fixed with min and max Y values ###
        //float maxY = Math.max(pad, parentH - vh - pad);

        float minY = (float)(0.2 * parentH);
        float maxY = (float)(0.7 * parentH);

        float x = pad + rng.nextFloat()*(maxX-pad);
        float y = minY + rng.nextFloat()*(maxY-minY);

        v.setX(x);
        v.setY(y);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            float x = ev.getRawX();
            float y = ev.getRawY();

            if (!isInsideView(mainButton, x, y) &&
                    !isInsideView(toggleSwitch, x, y) &&
                    !isInsideView(option, x, y)) {

                // Count mis-tap only if all tasks not yet finished
                if (!(buttonDone && toggleDone && optionDone)) {
                    // Fixed bug - kept adding mistaps on multiple runs since instance is always true inside it's own activity
                    //ExperimentResults.misClicks32++;
                }
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    private boolean isInsideView(View view, float x, float y) {

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];
        int right = left + view.getWidth();
        int bottom = top + view.getHeight();

        return x >= left && x <= right && y >= top && y <= bottom;
    }
}
