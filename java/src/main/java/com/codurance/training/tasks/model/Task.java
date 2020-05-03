package com.codurance.training.tasks.model;

import com.codurance.training.tasks.observer.Observable;

import java.util.Date;
import java.util.Objects;

public final class Task extends Observable {
    private final long id;
    private final String description;
    private boolean done;
    private Date deadline;
    private Date date;

    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = null;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    protected void setDone(boolean done) {
        this.done = done;
        observers.forEach(o -> o.update(this));
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline){
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                done == task.done &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, done);
    }

    @Override
    public Object getUpdate() {
        return null;
    }
}
