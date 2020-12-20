package com.pora.repairapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pora.lib_repairapp.JobStatus;
import com.pora.lib_repairapp.Repairman;

import java.util.Random;

public class addWorkerFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button btnRegister;
    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText email;
    private EditText phoneNumber;
    private String[] colors = {"rdeca", "roza", "rumena", "zelena", "modra", "oranzna"};
    private String[] furniture = {"miza", "omara"};

    private Spinner spinner;
    private String selectedItem;

    private ApplicationActivity app;

    private static final String TAG = "HALO";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_add_worker, container, false);

        btnRegister = view.findViewById(R.id.add_worker_register);

        spinner = view.findViewById(R.id.worker_status_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.worker_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        firstName = (EditText) view.findViewById(R.id.add_worker_firstname);
        lastName = (EditText) view.findViewById(R.id.add_worker_lastname);
        username = (EditText) view.findViewById(R.id.add_worker_username);
        email = (EditText) view.findViewById(R.id.add_worker_email_address);
        phoneNumber = (EditText) view.findViewById(R.id.add_worker_phone_number);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String rFirstName = firstName.getText().toString();
                String rLastName = lastName.getText().toString();
                String rUsername = username.getText().toString();
                String rEmail = email.getText().toString();
                String rPhoneNumber = phoneNumber.getText().toString();
                String password;

                Random rand = new Random();
                int rand1 = rand.nextInt(colors.length);
                int rand2 = rand.nextInt(furniture.length);
                int rand3 = rand.nextInt(1000);

                Log.i(TAG, rFirstName);
                Log.i(TAG, rLastName);
                Log.i(TAG, rUsername);
                Log.i(TAG, rEmail);
                Log.i(TAG, rPhoneNumber);

                if (rFirstName.isEmpty() || rLastName.isEmpty() || rUsername.isEmpty() || rEmail.isEmpty() || rPhoneNumber.isEmpty() || selectedItem.isEmpty()) {
                    Toast.makeText(getActivity(), "Prosimo izpolnite vsa polja", Toast.LENGTH_SHORT).show();
                } else {
                    password = colors[rand1] + furniture[rand2] + rand3;

                    if (selectedItem.equals("Vodja")) {
                        Repairman repairman = new Repairman(rFirstName, rLastName, rEmail, rPhoneNumber, rUsername, password, JobStatus.ADMIN);
                        app.addToRList(repairman);
                    } else {
                        Repairman repairman = new Repairman(rFirstName, rLastName, rEmail, rPhoneNumber, rUsername, password, JobStatus.NORMAL);
                        app.addToRList(repairman);
                    }
                    Toast.makeText(getActivity(), "Prosimo izpolnite vsa polja", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_addWorkerFragment_to_workerFragment);
                }

            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedItem = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void initData() {
        app = (ApplicationActivity) getActivity().getApplication();
    }
}