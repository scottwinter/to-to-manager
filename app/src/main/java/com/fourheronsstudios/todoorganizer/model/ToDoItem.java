package com.fourheronsstudios.todoorganizer.model;

public class ToDoItem {
    private String text;
    private int priority;

    public ToDoItem() {}
    public ToDoItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
