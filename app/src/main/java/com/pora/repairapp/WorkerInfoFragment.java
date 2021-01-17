package com.pora.repairapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkerInfoFragment extends Fragment {

    WorkerInfoFragmentArgs args;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_worker_info, container, false);

        /*int myNumber = args.getArgSelectedPosition();

        textView = view.findViewById(R.id.textView123);
        textView.setText(String.valueOf(myNumber));*/
        textView = view.findViewById(R.id.textView123);

        Bundle bundle = getArguments();
        int value = getArguments().getInt("position");

        textView.setText(String.valueOf(value));
        return view;
    }
}