package com.pora.repairapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pora.lib_repairapp.Repairman;

public class MyWorkerAdapter extends RecyclerView.Adapter<MyWorkerAdapter.MyViewHolder> {

    private ApplicationActivity app;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
        void onItemLongClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyWorkerAdapter(ApplicationActivity app) {
        this.app = app;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.my_woker_row, parent, false);
        MyWorkerAdapter.MyViewHolder viewHolder = new MyWorkerAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Repairman current = app.getrList().getRepairmanByPosition(position);
        holder.myText1.setText(current.getFirstName() + " " + current.getLastName());
        holder.myText2.setText(current.getPhoneNumber());

        holder.background.setBackgroundColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return app.getrList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;
        public View background;

        public MyViewHolder(@NonNull View v) {

            super(v);
            myText1 = itemView.findViewById(R.id.my_row_name);
            myText2 = itemView.findViewById(R.id.my_row_phone_number);
            background = itemView.findViewById(R.id.id_my_worker_row);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemLongClick(itemView, position);
                        }
                    }
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

}
