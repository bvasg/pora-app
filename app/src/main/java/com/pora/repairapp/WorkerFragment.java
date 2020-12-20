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
import com.pora.lib_repairapp.JobStatus;
import com.pora.lib_repairapp.RepairmanList;

/*/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkerFragment extends Fragment {

    private FloatingActionButton btnAdd;

    private ApplicationActivity app;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_worker, container, false);

        recyclerView = view.findViewById(R.id.workersRecyclerView);

        RepairmanList repairmanList = app.getrList();
        MyWorkerAdapter myWorkerAdapter = new MyWorkerAdapter(getActivity(), repairmanList);
        recyclerView.setAdapter(myWorkerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnAdd = view.findViewById(R.id.add_worker);
        btnAdd.setVisibility(view.VISIBLE);
        if(app.getRepairman().getStatus() == JobStatus.NORMAL) {
            btnAdd.setVisibility(view.GONE);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_workerFragment_to_addWorkerFragment);
            }
        });

        return view;
    }

    private void initData() {
        app = (ApplicationActivity) getActivity().getApplication();
    }
}