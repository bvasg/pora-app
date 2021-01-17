package com.pora.lib_repairapp;

import java.time.LocalDate;

public class Task {
    private String title;
    private LocalDate dueDate;
    private LocalDate acceptedDate;
    private String repairman;
    private Urgency urgency;
    private int progress;

    public Task(String title, LocalDate dueDate, LocalDate acceptedDate, String repairman, Urgency urgency, int progress) {
        this.title = title;
        this.dueDate = dueDate;
        this.acceptedDate = acceptedDate;
        this.repairman = repairman;
        this.urgency = urgency;
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", acceptedDate=" + acceptedDate +
                ", repairman=" + repairman +
                ", urgency=" + urgency +
                ", progress=" + progress +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(LocalDate acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public String getRepairman() {
        return repairman;
    }

    public void setRepairman(String repairman) {
        this.repairman = repairman;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Urgency getUrgency() {
        return urgency;
    }
}
