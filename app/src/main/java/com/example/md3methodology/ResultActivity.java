package com.example.md3methodology;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView results = findViewById(R.id.resultsText);

        String output =
                "48dp Time: " + ExperimentResults.time48 + " ms\n" +
                        "48dp MisTaps: " + ExperimentResults.misTaps48 + "\n\n" +
                        "32dp Time: " + ExperimentResults.time32 + " ms\n" +
                        "32dp MisTaps: " + ExperimentResults.misTaps32;

        results.setText(output);
    }
}