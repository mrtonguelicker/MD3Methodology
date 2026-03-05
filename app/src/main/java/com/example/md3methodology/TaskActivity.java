package com.example.md3methodology;

import java.util.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.util.*;
import android.view.*;

public class TaskActivity extends AppCompatActivity{
    private FrameLayout root;
    private TextView trialCounter;
    private Button mainButton;
    private SwitchCompat toggle;
    private RadioButton option;

    // CHANGE THIS FOR HOW MANY RUNS PER TARGET SIZE
    private static final int TRIALS_PER_CONDITION = 15;

    public int trial = 0;
    public long startTime = 0;
    public long totalTime = 0;

    private boolean buttonDone = false;
    private boolean toggleDone = false;
    private boolean optionDone = false;
    private boolean resetting = false;
    private int sizeDp;

    //THIS AFFECTS THE LIST OF EXPERIMENTAL TARGET VALUES
    public static final int[] SIZES_DP = {40, 24, 56, 32, 48};
    private int targetIndex;
    private String next;
    Random rng = new Random();

    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_task);

        // targetIndex = 1; (deprecated)

        targetIndex = getIntent().getIntExtra("targetIndex", 0); //SIZES_DP[targetIndex]; (deprecated)
        sizeDp = SIZES_DP[targetIndex];

        root = findViewById(R.id.taskRoot);
        trialCounter = findViewById(R.id.trialCounter);
        mainButton = findViewById(R.id.mainButton);
        toggle = findViewById(R.id.toggle);
        option = findViewById(R.id.option);

        int sizePx = dptoPx(sizeDp);

        setSquareSize(mainButton, sizePx);
        setSquareSize(toggle, sizePx);
        setSquareSize(option, sizePx);

        if (targetIndex + 1 < SIZES_DP.length){
            next = "Next: " + SIZES_DP[targetIndex+1] + "dp";
        }
        else{
            next = "Next: Results";
        }

        trialCounter.setText("Target: " + sizeDp + "dp | " + next);

        mainButton.setOnClickListener(v -> {
            buttonDone = true;
            checkCompletion();
        });

        toggle.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (resetting) return;
            if (isChecked) {
                toggleDone = true;
                checkCompletion();
            }
        }));

        option.setOnClickListener(v -> {
            optionDone = true;
            checkCompletion();
        });

        root.post(this::startTrial);

    }

    private int dptoPx (int dp){
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()));
    }

    private void setSquareSize (View v, int sizePx){
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        layoutParams.width = sizePx;
        layoutParams.height = sizePx;
        v.setLayoutParams(layoutParams);
    }

    private void startTrial(){
        trialCounter.setText("Target " + sizeDp + "dp | Trial " + (trial + 1) + "/" + TRIALS_PER_CONDITION +
                " | " + next);

        resetAll();
        randomizePositions();
        startTime = SystemClock.elapsedRealtime();
    }

    private void checkCompletion() {
        if (buttonDone && toggleDone && optionDone) {

            long trialTime = SystemClock.elapsedRealtime() - startTime;
            totalTime += trialTime;
            trial++;
            ExperimentResults.runs[targetIndex] = trial;

            if(trial < TRIALS_PER_CONDITION) {
                startTrial();
                return;
            }
            ExperimentResults.totalTime[targetIndex] = totalTime;

            startActivity(new Intent(this, TransitionActivity.class).putExtra("targetIndex", targetIndex));
            finish();
        }
    }

    private void randomizePositions() {
        int w = root.getWidth();
        int h = root.getHeight();
        int pad = 50;

        placeViewRandom(mainButton, w, h, pad);
        placeViewRandom(toggle, w, h, pad);
        placeViewRandom(option, w, h, pad);
    }

    private void placeViewRandom(View v, int parentW, int parentH, int pad){
        int vw = v.getWidth();
        int vh = v.getHeight();

        float maxX = Math.max(pad, parentW - vw - pad);

        // ### deprecated, fixed with min and max Y values ###
        //float maxY = Math.max(pad, parentH - vh - pad);

        float minY = (float) (0.2 * parentH);
        float maxY = (float) (0.7 * parentH);

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
                !isInsideView(toggle, x, y) &&
                !isInsideView(option, x, y)) {

            // Count mis-tap only if all tasks not yet finished
            if (!(buttonDone && toggleDone && optionDone)) {

                // Fixed bug - kept adding mistaps on multiple runs since instance is always true inside it's own activity
                ExperimentResults.misClicks[targetIndex]++;
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
    private void resetAll(){
        buttonDone = false;
        toggleDone = false;
        optionDone = false;

        resetting = true;
        toggle.setChecked(false);
        option.setChecked(false);
        resetting = false;
    }
}
