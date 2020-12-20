package com.pora.repairapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pora.lib_repairapp.TaskList;


public class TaskFragment extends Fragment {

    private FloatingActionButton btnAdd;

    private ApplicationActivity app;
    private RecyclerView recyclerView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initData();
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        recyclerView = view.findViewById(R.id.tasksRecyclerView);

        TaskList taskList = app.getTList();
        MyTaskAdapter myTaskAdapter = new MyTaskAdapter(getActivity(), taskList);
        recyclerView.setAdapter(myTaskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnAdd = view.findViewById(R.id.add_task);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_taskFragment_to_addTaskFragment);
            }
        });



        return view;
    }

    private void initData() {
        app = (ApplicationActivity) getActivity().getApplication();
    }
}