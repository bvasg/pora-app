package com.pora.repairapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.pora.lib_repairapp.RepairmanList;
import com.pora.lib_repairapp.TaskList;

public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.MyTaskViewHolder> {

    TaskList list;
    Context context;

    public MyTaskAdapter(Context context, TaskList list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_task_row, parent, false);

        return new MyTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTaskViewHolder holder, int position) {
        holder.myText1.setText(list.getTaskByPosition(position).getTitle());
        holder.myText2.setText(list.getTaskByPosition(position).getRepairman());
        holder.myText3.setText("(" + list.getTaskByPosition(position).getDueDate().toString() + ")");
        holder.myText4.setText(String.valueOf(list.getTaskByPosition(position).getProgress()) + "%");

        /*holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkerFragmentDirections.ActionWorkerFragmentToWorkerInfoFragment action = WorkerFragmentDirections.actionWorkerFragmentToWorkerInfoFragment(list.getRepairmanByPosition(position).getPhoneNumber());
                Navigation.findNavController(view).navigate(action);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyTaskViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3, myText4;
        ConstraintLayout constraintLayout;

        public MyTaskViewHolder(@NonNull View itemView) {

            super(itemView);
            myText1 = itemView.findViewById(R.id.my_task_row_name);
            myText2 = itemView.findViewById(R.id.my_task_row_worker);
            myText3 = itemView.findViewById(R.id.my_task_row_date);
            myText4 = itemView.findViewById(R.id.my_task_row_progress);
            constraintLayout = itemView.findViewById(R.id.id_my_task_row);
        }
    }

}
