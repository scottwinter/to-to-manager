package com.fourheronsstudios.todoorganizer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.fourheronsstudios.todoorganizer.model.ToDoItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DeleteToDoDialog extends DialogFragment {
    private DeleteToDoDialogListener deleteToDoDialogListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you really want to delete this todo?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        deleteToDoDialogListener.deleteTodo();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface DeleteToDoDialogListener {
        void deleteTodo(ToDoItem todoItem);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            deleteToDoDialogListener = (DeleteToDoDialog.DeleteToDoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Must implement ToDoItemListener");
        }
    }
}
