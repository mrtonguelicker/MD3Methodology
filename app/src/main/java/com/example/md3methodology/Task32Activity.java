package com.example.md3methodology;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class Task32Activity extends AppCompatActivity {

    long startTime;

    boolean buttonDone = false;
    boolean toggleDone = false;
    boolean optionDone = false;

    Button mainButton;
    Switch toggleSwitch;
    RadioButton option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task32);

        mainButton = findViewById(R.id.mainButton32);
        toggleSwitch = findViewById(R.id.toggle32);
        option = findViewById(R.id.option32);

        startTime = SystemClock.elapsedRealtime();

        mainButton.setOnClickListener(v -> {
            buttonDone = true;
            checkCompletion();
        });

        toggleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleDone = true;
            checkCompletion();
        });

        option.setOnClickListener(v -> {
            optionDone = true;
            checkCompletion();
        });
    }

    private void checkCompletion() {
        if (buttonDone && toggleDone && optionDone) {

            long totalTime = SystemClock.elapsedRealtime() - startTime;

            ExperimentResults.time32 = totalTime;

            startActivity(new Intent(this, ResultActivity.class));
            finish();
        }
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

                    if (this instanceof Task32Activity)
                        ExperimentResults.misTaps48++;
                    else
                        ExperimentResults.misTaps32++;
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
