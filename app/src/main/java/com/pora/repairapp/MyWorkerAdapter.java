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

public class MyWorkerAdapter extends RecyclerView.Adapter<MyWorkerAdapter.MyViewHolder> {

    RepairmanList list;
    Context context;

    public MyWorkerAdapter(Context context, RepairmanList list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_woker_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText1.setText(list.getRepairmanByPosition(position).getFirstName() + " " + list.getRepairmanByPosition(position).getLastName());
        holder.myText2.setText(list.getRepairmanByPosition(position).getPhoneNumber());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkerFragmentDirections.ActionWorkerFragmentToWorkerInfoFragment action = WorkerFragmentDirections.actionWorkerFragmentToWorkerInfoFragment(list.getRepairmanByPosition(position).getPhoneNumber());
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            myText1 = itemView.findViewById(R.id.my_row_name);
            myText2 = itemView.findViewById(R.id.my_row_phone_number);
            constraintLayout = itemView.findViewById(R.id.id_my_row);
        }
    }

}
