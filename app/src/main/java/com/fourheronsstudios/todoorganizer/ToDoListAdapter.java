package com.fourheronsstudios.todoorganizer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {
    private ArrayList<String> mDataset;

    ToDoListAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ToDoListAdapter.ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ToDoViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.to_do_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        holder.toDoText.setText(mDataset.get(position));
        holder.toDoDate.setText("3/3/2020");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ToDoViewHolder extends RecyclerView.ViewHolder {
        TextView toDoText;
        TextView toDoDate;
        ToDoViewHolder(View itemView) {
            super(itemView);
            toDoText = itemView.findViewById(R.id.to_do_item_text);
            toDoDate = itemView.findViewById(R.id.to_do_item_date);

        }
    }
}
