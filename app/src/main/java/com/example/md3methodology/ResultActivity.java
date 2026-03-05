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


        // ### DEPRECATED CODE BLOCK

        /*TextView time48 = findViewById(R.id.time48);
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
        mis48.setText("Misclicks: " + ExperimentResults.misClicks48);
        runs48.setText("Runs: " + ExperimentResults.runs48);
        Davg48.setText("Average: " + avg48 + "ms");

        long avg32 = ExperimentResults.time32 / ExperimentResults.runs32;
        time32.setText("Time: " + ExperimentResults.time32 + " ms");
        mis32.setText("Misclicks: " + ExperimentResults.misClicks32);
        runs32.setText("Runs: " + ExperimentResults.runs32);
        Davg32.setText("Average: " + avg32 + " ms");*/

    }
}