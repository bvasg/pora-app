package com.pora.repairapp;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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
import java.util.Calendar;


public class addTaskFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView progressTextView;
    ProgressBar progressBar;
    SeekBar seekBar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView dueDate;
    Button button;
    Spinner spinner;
    private String selectedRepairam;
    int progress;
    EditText title;

    Button pickButton;
    Calendar calendar;
    DatePickerDialog datePickerDialog;

    private ApplicationActivity app;
    View itemView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initData();
        itemView = inflater.inflate(R.layout.fragment_add_task, container, false);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        radioGroup = itemView.findViewById(R.id.radio_group);
        button = itemView.findViewById(R.id.button_add_new_task);
        progressTextView = (TextView) itemView.findViewById(R.id.infoTextProgress);
        seekBar = (SeekBar) itemView.findViewById(R.id.infoSeekBar);
        progressBar = (ProgressBar) itemView.findViewById(R.id.infoProgressBar);
        dueDate = (TextView) itemView.findViewById(R.id.task_duedate);
        spinner = (Spinner) itemView.findViewById(R.id.spinner_list_of_workers);
        pickButton = (Button) itemView.findViewById(R.id.pickDate);
        title = (EditText) itemView.findViewById(R.id.task_title);

        ArrayList<String> arrayList= new ArrayList<String>();
        RepairmanList rList = app.getrList();
        for(int i = 0; i < rList.size(); i++) {
            arrayList.add(rList.getRepairmanByPosition(i).getFirstName() + " " + rList.getRepairmanByPosition(i).getLastName());
        }

        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        dueDate.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });

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
                LocalDate ddate = LocalDate.parse(dueDate.getText().toString(), formatter);

                String x = LocalDate.now().toString();
                LocalDate sdate = LocalDate.parse(x);

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = itemView.findViewById(radioId);

                if(title.getText().toString().matches("") || selectedRepairam.isEmpty()) {
                    Toast.makeText(getActivity(), "Izpolnite obvezna polja", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Dodano novo opravilo", Toast.LENGTH_SHORT).show();
                    if(radioButton.getText().toString().equals("Normalno")) {
                        Log.i("Test", "Normalno");
                        Task task = new Task(title.getText().toString(), ddate, sdate, selectedRepairam, Urgency.LOW, progress);
                        app.addToTList(task);
                        app.saveTasksData();
                    } else if(radioButton.getText().equals("Nujno")) {
                        Log.i("Test", "Nujno");
                        Task task = new Task(title.getText().toString(), ddate, sdate, selectedRepairam, Urgency.MEDIUM, progress);
                        app.addToTList(task);
                        app.saveTasksData();
                    } else if(radioButton.getText().equals("Zelo nujno")) {
                        Log.i("Test", "Zelo nujno");
                        Task task = new Task(title.getText().toString(), ddate, sdate, selectedRepairam, Urgency.HIGH, progress);
                        app.addToTList(task);
                        app.saveTasksData();
                    }

                }
                Log.i("Test", title.getText().toString());
                Log.i("Test", ddate.toString());
                Log.i("Test", sdate.toString());
                Log.i("Test", selectedRepairam);
                Log.i("Test", String.valueOf(progress));
                Log.i("Test", radioButton.getText().toString());

                Navigation.findNavController(view).navigate(R.id.action_addTaskFragment_to_taskFragment);

            }
        });

        return itemView;
    }


    /*public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = view.findViewById(radioId);
    }*/

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