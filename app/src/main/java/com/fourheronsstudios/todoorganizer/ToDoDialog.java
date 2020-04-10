package com.fourheronsstudios.todoorganizer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import timber.log.Timber;

public class ToDoDialog extends DialogFragment {
    private EditText toDoItem;
    private ToDoDialogListener toDoDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_todo_dialog, null);
        builder.setView(view)
                .setTitle("Create New Todo")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToDoDialog.this.getDialog().cancel();
                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String item = toDoItem.getText().toString();
                        toDoDialogListener.applyText(item);
                    }
                });
        toDoItem = view.findViewById(R.id.to_do_item_text);

        return builder.create();
    }

    public interface ToDoDialogListener {
        void applyText(String todoItem);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            toDoDialogListener = (ToDoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Must implement ToDoItemListener");
        }
    }
}
