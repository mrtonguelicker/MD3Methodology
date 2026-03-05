package com.example.md3methodology;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import kotlinx.coroutines.scheduling.Task;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(v -> {
            ExperimentResults.init(TaskActivity.SIZES_DP.length);

            Intent intent = new Intent(this, TaskActivity.class);
            intent.putExtra("targetIndex", 0);
            startActivity(intent);
        });
    }
}