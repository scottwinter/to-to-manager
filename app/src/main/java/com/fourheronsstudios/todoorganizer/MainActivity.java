package com.fourheronsstudios.todoorganizer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fourheronsstudios.todoorganizer.model.ToDoItem;
import com.fourheronsstudios.todoorganizer.repository.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MyLogs";
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        mydb.insertTodo("test todo in SQLite", 2);
        mydb.insertTodo("test todo in SQLite #2", 1);
        ArrayList<ToDoItem> toDos = mydb.getAllTodos();
        Timber.i("Database todo's: %s", toDos);

        for(ToDoItem item : toDos) {
            mydb.deleteTodos(item.getId());
        }

        RecyclerView recyclerView = findViewById(R.id.to_do_list_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new ToDoListAdapter(populateData()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click.
                Log.d(TAG, "This is a log from the FAB");
                Timber.d("This is a log message from Timber");
            }
        });
    }

    private ArrayList<ToDoItem> populateData() {
        ArrayList<ToDoItem> dataList = new ArrayList<>();
        for(int i = 0; i <= 20; i++) {
            ToDoItem item = new ToDoItem("This is a to-do list item");
            dataList.add(item);
        }

        return dataList;
    }
}
