package com.tasklist;

import lombok.Getter;
import lombok.Setter;

public class Task {
    private String description;
    private boolean completed = false;


    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void switchCompleted(){
        completed = !completed;
    }

    public boolean isComplete() {
        return completed;
    }

    public void setComplete(boolean b) {

    }
}
