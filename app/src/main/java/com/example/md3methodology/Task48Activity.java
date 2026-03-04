package com.example.md3methodology;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class Task48Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_task48);

        mainButton = findViewById(R.id.mainButton48);
        toggleSwitch = findViewById(R.id.toggle48);
        option = findViewById(R.id.option48);

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

            ExperimentResults.time48 = totalTime;

            startActivity(new Intent(this, Task32Activity.class));
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

                    // Fixed bug - kept adding mistaps on multiple runs since instance is always true inside it's own activity
                    ExperimentResults.misTaps48++;
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

