package com.fourheronsstudios.todoorganizer;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fourheronsstudios.todoorganizer.model.ToDoItem;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {
    private ArrayList<ToDoItem> mDataset;

    ToDoListAdapter(ArrayList<ToDoItem> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ToDoListAdapter.ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ToDoViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.to_do_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        holder.toDoText.setText(mDataset.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ToDoViewHolder extends RecyclerView.ViewHolder {
        TextView toDoText;
        ToDoViewHolder(final View itemView) {
            super(itemView);
            toDoText = itemView.findViewById(R.id.to_do_item_text);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Dialog dialog = new Dialog(itemView.getContext());
//                    dialog.setContentView(R.layout.item);
                    dialog.setTitle("Do you really want to delete this todo?" );
                    dialog.setCancelable(true);

                    dialog.show();
                    return false;
                }
            });
        }

    }
}
