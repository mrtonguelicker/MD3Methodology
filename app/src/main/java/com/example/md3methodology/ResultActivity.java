package com.example.md3methodology;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView time48 = findViewById(R.id.time48);
        TextView mis48 = findViewById(R.id.mis48);
        TextView runs48 = findViewById(R.id.runs48);
        TextView Davg48 = findViewById(R.id.avg48);
        TextView time32 = findViewById(R.id.time32);
        TextView mis32 = findViewById(R.id.mis32);
        TextView runs32 = findViewById(R.id.runs32);
        TextView Davg32 = findViewById(R.id.avg32);

        Button homeButton = findViewById(R.id.homeButton);

        long avg48 = ExperimentResults.time48 / ExperimentResults.runs48;
        time48.setText("Time: " + ExperimentResults.time48 + " ms");
        mis48.setText("MisTaps: " + ExperimentResults.misTaps48);
        runs48.setText("Runs: " + ExperimentResults.runs48);
        Davg48.setText("Average: " + avg48 + "ms");

        long avg32 = ExperimentResults.time32 / ExperimentResults.runs32;
        time32.setText("Time: " + ExperimentResults.time32 + " ms");
        mis32.setText("MisTaps: " + ExperimentResults.misTaps32);
        runs32.setText("Runs: " + ExperimentResults.runs32);
        Davg32.setText("Average: " + avg32 + " ms");

        homeButton.setOnClickListener(v -> {

            ExperimentResults.reset();

            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}