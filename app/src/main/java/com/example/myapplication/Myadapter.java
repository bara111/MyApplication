package com.example.myapplication;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Todo> items;
    private OnItemClickListener onItemClickListener;

    public Myadapter(ArrayList<Todo> items) {
        this.items = items;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    public void updateData(ArrayList<Todo> viewModels) {
        items.clear();
        items.addAll(viewModels);
        notifyDataSetChanged();
    }
    public void addItem(int position, Todo viewModel) {
        items.add(position, viewModel);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Todo item = items.get(position);
        holder.task.setText(item.getName());
        if(item.isCompleted())
        {        holder.checkBox.setChecked(true);


        }
        else {
                  holder.checkBox.setChecked(false);

            }
        holder.task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"bara",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (ViewModel) v.getTag());
                }
            }, 0);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView task, credit, title, description;

        public ViewHolder(View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.task);
            checkBox=itemView.findViewById(R.id.status);


        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, ViewModel viewModel);

    }
}