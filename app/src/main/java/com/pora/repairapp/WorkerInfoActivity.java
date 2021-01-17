package com.pora.repairapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.pora.lib_repairapp.Repairman;

public class WorkerInfoActivity extends AppCompatActivity {
    ApplicationActivity app;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_info);
        initData();

        firstName = findViewById(R.id.editTextTextPersonName);
        lastName = findViewById(R.id.editTextTextPersonName2);
        email = findViewById(R.id.editTextTextPersonName3);
        phoneNumber = findViewById(R.id.editTextTextPersonName4);

        Intent intent = getIntent();
        int number = intent.getIntExtra("position", 100);

        Repairman current = app.getrList().getRepairmanByPosition(number);
        firstName.setText(current.getFirstName());
        firstName.setEnabled(false);

        lastName.setText(current.getLastName());
        lastName.setEnabled(false);

        email.setText(current.getEmailAddress());
        email.setEnabled(false);

        phoneNumber.setText(current.getPhoneNumber());
        phoneNumber.setEnabled(false);
    }

    private void initData() {
        app = (ApplicationActivity) getApplication();
    }
}