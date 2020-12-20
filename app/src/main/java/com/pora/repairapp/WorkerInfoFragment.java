package com.pora.repairapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WorkerInfoFragment extends Fragment {

    WorkerInfoFragmentArgs args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_worker_info, container, false);

        String phoneNumeber = args.getPhoneNumber();
        TextView txtView = view.findViewById(R.id.worker_info_phone);
        txtView.setText(phoneNumeber);
        return view;
    }
}