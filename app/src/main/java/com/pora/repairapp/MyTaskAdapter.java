package com.pora.repairapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pora.lib_repairapp.Task;


public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.MyTaskViewHolder> {

    private ApplicationActivity app;
    private MyTaskAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
        void onItemLongClick(View itemView, int position);
    }

    public void setOnItemClickListener(MyTaskAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyTaskAdapter(ApplicationActivity app) {
        this.app = app;
    }


    @NonNull
    @Override
    public MyTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.my_task_row, parent, false);
        MyTaskAdapter.MyTaskViewHolder viewHolder = new MyTaskAdapter.MyTaskViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTaskViewHolder holder, int position) {
        final Task current = app.getTList().getTaskByPosition(position);
        holder.myText1.setText(current.getTitle());
        holder.myText2.setText(current.getRepairman());
        holder.myText3.setText("(" + current.getDueDate().toString() + ")");
        holder.myText4.setText(String.valueOf(current.getProgress()) + "%");

        holder.background.setBackgroundColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return app.getTList().size();
    }

    public class MyTaskViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3, myText4;
        public View background;

        public MyTaskViewHolder(@NonNull View v) {

            super(v);
            myText1 = itemView.findViewById(R.id.my_task_row_name);
            myText2 = itemView.findViewById(R.id.my_task_row_worker);
            myText3 = itemView.findViewById(R.id.my_task_row_date);
            myText4 = itemView.findViewById(R.id.my_task_row_progress);
            background = itemView.findViewById(R.id.id_my_task_row);

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
