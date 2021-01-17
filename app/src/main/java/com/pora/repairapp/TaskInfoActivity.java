package com.pora.repairapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pora.lib_repairapp.Task;
import com.pora.lib_repairapp.Urgency;

public class TaskInfoActivity extends AppCompatActivity {
    ApplicationActivity app;
    private EditText title;
    private EditText acceptedDate;
    private EditText dueDate;
    private EditText responsiblePerson;
    private EditText urgency;

    int progress;
    TextView progressTextView;
    ProgressBar progressBar;
    SeekBar seekBar;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        initData();

        Intent intent = getIntent();
        number = intent.getIntExtra("position", 100);

        title = findViewById(R.id.infoTitle);
        acceptedDate = findViewById(R.id.infoAcceptedDate);
        dueDate = findViewById(R.id.infoDueDate);
        responsiblePerson = findViewById(R.id.infoResponsiblePerson);
        urgency = findViewById(R.id.infoUrgency);

        progressTextView = findViewById(R.id.infoTextProgress);
        progressBar = findViewById(R.id.infoProgressBar);
        seekBar = findViewById(R.id.infoSeekBar);

        Task current = app.getTList().getTaskByPosition(number);

        title.setText(current.getTitle());
        title.setEnabled(false);

        acceptedDate.setText(current.getAcceptedDate().toString());
        acceptedDate.setEnabled(false);

        dueDate.setText(current.getDueDate().toString());
        dueDate.setEnabled(false);

        responsiblePerson.setText(current.getRepairman());
        responsiblePerson.setEnabled(false);

        if (current.getUrgency() == Urgency.LOW) {
            urgency.setText("Normalno");
            urgency.setEnabled(false);

        } else if (current.getUrgency() == Urgency.MEDIUM) {
            urgency.setText("Nujno");
            urgency.setEnabled(false);

        } else if(current.getUrgency() == Urgency.HIGH) {
            urgency.setText("Zelo nujno");
            urgency.setEnabled(false);

        }

        progress = current.getProgress();
        seekBar.setProgress(progress);
        progressBar.setProgress(progress);
        progressTextView.setText("" + progress + "%");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressBar.setProgress(i);
                progressTextView.setText("" + i + "%");
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void initData() {
        app = (ApplicationActivity) getApplication();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        app.getTList().getTaskByPosition(number).setProgress(progress);
    }
}