package com.fourheronsstudios.todoorganizer.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fourheronsstudios.todoorganizer.model.ToDoItem;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "toDoList";
    private static final String TABLE_NAME = "todos";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table todos " +
                        "(id integer primary key, todo text, priority integer)"
        );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todos");
        onCreate(db);
    }

    public boolean insertTodo (String todo, int priority) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("todo", todo);
        contentValues.put("priority", priority);
        db.insert("todos", null, contentValues);
        return true;
    }

    public Cursor getTodo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from todos where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public Integer deleteTodos (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<ToDoItem> getAllTodos() {
        ArrayList<ToDoItem> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from todos", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            ToDoItem item = new ToDoItem(
                    res.getInt(res.getColumnIndex("id")),
                    res.getString(res.getColumnIndex("todo")),
                    res.getInt(res.getColumnIndex("priority")));
            array_list.add(item);
            res.moveToNext();
        }
        db.close();
        return array_list;
    }
}
