package com.example.md3methodology;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class TransitionActivity extends AppCompatActivity{

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        TextView stats = findViewById(R.id.transitionStats);
        TextView lowerText = findViewById(R.id.lower_text);
        TextView subtitle = findViewById(R.id.transitionSubtitle);
        Button nextButton = findViewById(R.id.nextButton);

        // ### DEPRECATED CODE BLOCK ###

        /*TextView stats = findViewById(R.id.transitionStats);
        Button nextButton = findViewById(R.id.nextButton);

        long total = ExperimentResults.time48;
        int misclicks = ExperimentResults.misTaps48;
        int runs = ExperimentResults.runs48;
        long average = total/runs;

        stats.setText(
                "Time: " + total + " ms\n" +
                "Misclicks: " + misclicks +
                "\nRuns: " + runs +
                "\nAverage: " + average + " ms"
        );

        nextButton.setOnClickListener(v -> {
            startActivity(new Intent(this, Task32Activity.class));
            finish();
        })*/

        int currentIndex = getIntent().getIntExtra("targetIndex", 0);
        int sizeDp = TaskActivity.SIZES_DP[currentIndex];

        long total = ExperimentResults.totalTime[currentIndex];
        int runs = ExperimentResults.runs[currentIndex];
        int misses = ExperimentResults.misClicks[currentIndex];
        long avg = total/runs;

        stats.setText(
                "Completed " + sizeDp + "dp targets\n\n" +
                        "Runs: " + runs + "\n" +
                        "Total time: " + total + " ms\n" +
                        "Average: " + avg + " ms\n" +
                        "Misclicks: " + misses
        );

        int nextIndex = currentIndex + 1;
        if (!(nextIndex < TaskActivity.SIZES_DP.length)){
            subtitle.setText("");
            lowerText.setText("You have now reached the end of the experiment. Click next to see the results.");
        }

        nextButton.setOnClickListener(v -> {

            if (nextIndex < TaskActivity.SIZES_DP.length){
                Intent intent = new Intent(this, TaskActivity.class);
                intent.putExtra("targetIndex", nextIndex);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);
            }
            finish();
        });



    }

}
