package com.example.md3methodology;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import kotlin.io.LineReader;
import kotlinx.coroutines.scheduling.Task;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LinearLayout container = findViewById(R.id.resultsContainer);
        Button home = findViewById(R.id.homeButton);

        LayoutInflater inflater = LayoutInflater.from(this);

        List<Integer> sortHelper = new ArrayList<>();

        for (int i = 0; i < TaskActivity.SIZES_DP.length; i++){
            sortHelper.add(i);
        }

        sortHelper.sort((a,b) -> Integer.compare(TaskActivity.SIZES_DP[b],(TaskActivity.SIZES_DP[a])));
        for (int i : sortHelper){
            View card = inflater.inflate(R.layout.item_result_scroll, container, false);

            int sizeDp = TaskActivity.SIZES_DP[i];
            long total = ExperimentResults.totalTime[i];
            int runs = ExperimentResults.runs[i];
            int misses = ExperimentResults.misClicks[i];
            long avg = total/runs;

            TextView title = card.findViewById(R.id.title);
            TextView time = card.findViewById(R.id.time);
            TextView runsTv = card.findViewById(R.id.runs);
            TextView avgTv = card.findViewById(R.id.avg);
            TextView misTv = card.findViewById(R.id.mis);

            title.setText(sizeDp + "dp Targets");
            time.setText("Total time: " + total + " ms");
            runsTv.setText("Runs: " + runs);
            avgTv.setText("Average: " + avg + " ms");
            misTv.setText("Misclicks: " + misses);

            container.addView(card);

        }

        home.setOnClickListener(v -> {

            ExperimentResults.reset();

            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}