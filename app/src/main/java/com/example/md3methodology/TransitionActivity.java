package com.example.md3methodology;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class TransitionActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        TextView stats = findViewById(R.id.transitionStats);
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
        });

    }

}
