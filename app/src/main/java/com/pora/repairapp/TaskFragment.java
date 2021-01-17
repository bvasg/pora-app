package com.pora.repairapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class TaskFragment extends Fragment implements MyTaskAdapter.OnItemClickListener {

    private FloatingActionButton btnAdd;

    private ApplicationActivity app;
    private RecyclerView recyclerView;
    private int selectedPosition;
    private MyTaskAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        adapter = new MyTaskAdapter(app);
        recyclerView = view.findViewById(R.id.tasksRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(View itemView, int position) {
        Intent intent = new Intent(getActivity(), TaskInfoActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View itemView, int position) {
        selectedPosition = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Izbris opravila " + app.getTList().getTaskByPosition(position).getTitle() + " (" + app.getTList().getTaskByPosition(position).getDueDate().toString() + ")").setMessage("Ste prepričani?").setPositiveButton("Da", dialogClickListener).setNegativeButton("Ne", dialogClickListener).show();
    }

    public void removeRow(int position) {
        app.getTList().remove(position);
        app.saveTasksData();
        adapter.notifyDataSetChanged();
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    removeRow(selectedPosition);
                    //  Toast.makeText(getBaseContext(),"Selected YES:"+data.getList().get(pos), Toast.LENGTH_LONG).show();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    //Toast.makeText(getBaseContext(),"Selected NO:"+data.getList().get(pos), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
}