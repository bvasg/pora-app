package com.pora.repairapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pora.lib_repairapp.RepairmanList;
import com.pora.lib_repairapp.Task;
import com.pora.lib_repairapp.Urgency;

import java.time.format.DateTimeFormatter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;


public class addTaskFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView progressTextView;
    ProgressBar progressBar;
    SeekBar seekBar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText dueDate;
    Button button;
    Spinner spinner;
    private String selectedRepairam;
    int progress;

    private ApplicationActivity app;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initData();
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US);

        radioGroup = view.findViewById(R.id.radio_group);
        button = view.findViewById(R.id.button_add_new_task);
        progressTextView = (TextView) view.findViewById(R.id.text_view_progress);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        dueDate = (EditText) view.findViewById(R.id.task_duedate);
        spinner = (Spinner) view.findViewById(R.id.spinner_list_of_workers);

        ArrayList<String> arrayList= new ArrayList<String>();
        RepairmanList rList = app.getrList();
        for(int i = 0; i < rList.size(); i++) {
            arrayList.add(rList.getRepairmanByPosition(i).getFirstName() + " " + rList.getRepairmanByPosition(i).getLastName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

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

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String title = progressTextView.getText().toString();
                LocalDate ddate = LocalDate.parse(dueDate.getText().toString());

                String x = LocalDate.now().toString();
                LocalDate sdate = LocalDate.parse(x);

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);

                if(!title.isEmpty() || selectedRepairam.isEmpty()) {
                    Toast.makeText(getActivity(), "Izpolnite obvezna polja", Toast.LENGTH_SHORT).show();
                } else {
                    if(radioButton.getText() == "Normalno") {
                        Task task = new Task(title, ddate, sdate, selectedRepairam, Urgency.LOW, progress);
                        app.addToTList(task);
                    } else if(radioButton.getText() == "Nujno") {
                        Task task = new Task(title, ddate, sdate, selectedRepairam, Urgency.MEDIUM, progress);
                        app.addToTList(task);
                    } else if(radioButton.getText() == "Zelo nujno") {
                        Task task = new Task(title, ddate, sdate, selectedRepairam, Urgency.HIGH, progress);
                        app.addToTList(task);
                    }
                }
            }
        });

        return view;
    }

    public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = view.findViewById(radioId);
    }

    private void initData() {
        app = (ApplicationActivity) getActivity().getApplication();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedRepairam = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}