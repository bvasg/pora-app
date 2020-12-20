package com.pora.repairapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pora.lib_repairapp.Repairman;
import com.pora.lib_repairapp.RepairmanList;

public class MainActivity extends AppCompatActivity {

    private  final String TAG = "LOGIN";

    private EditText username;
    private EditText password;
    private Button btnLogin;
    private Button btnRegister;
    private TextView textViewInfo;

    private ApplicationActivity app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.button_login);
        textViewInfo = findViewById(R.id.login_info);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = username.getText().toString();
                String inputPassword = password.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nepravilni podatki", Toast.LENGTH_SHORT).show();
                    app.decrementTries();
                    textViewInfo.setText("Število poskusov: " + app.getNumberOfTries());

                } else {

                    if(validate(inputName, inputPassword)) {
                        Toast.makeText(MainActivity.this, "Uspešna prijava", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, PageActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Prosimo vnesite pravilne podatke", Toast.LENGTH_SHORT).show();
                        app.decrementTries();
                        textViewInfo.setText("Število poskusov: " + app.getNumberOfTries());

                    }

                }

                if(app.getNumberOfTries() <= 0) {
                    btnLogin.setEnabled(false);
                }

            }
        });

        initData();
    }

    private boolean validate(String username, String password) {

        RepairmanList repairmanList = app.getrList();
        Repairman man = repairmanList.getRepairman(username);

        if(man.getUsername().toLowerCase().equals(username.toLowerCase()) && man.getPassword().equals(password)) {
            app.setRepairman(man);
            return true;
        }
        return false;
    }

    private void initData() {
        app = (ApplicationActivity) getApplication();
    }
}