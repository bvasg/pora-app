package com.pora.repairapp;

import android.app.Application;

import com.pora.lib_repairapp.JobStatus;
import com.pora.lib_repairapp.Repairman;
import com.pora.lib_repairapp.RepairmanList;
import com.pora.lib_repairapp.Task;
import com.pora.lib_repairapp.TaskList;
import com.pora.lib_repairapp.Urgency;

import java.time.LocalDate;

public class ApplicationActivity extends Application {
    private int numberOfTries;
    private RepairmanList rList = new RepairmanList();
    private TaskList tList = new TaskList();
    private Repairman repairman;

    @Override
    public void onCreate() {
        super.onCreate();
        this.numberOfTries = 5;
        this.rList.add(new Repairman("Blaz", "Glogovcan", "blaz.glogovcan@gmail.com", "041 987 626", "blazg", "blaz123", JobStatus.ADMIN));
        this.rList.add(new Repairman("Martin", "Domajnko", "domajnko.martin@gmail.com", "041 123 456", "martind", "martin123", JobStatus.NORMAL));
        this.tList.add(new Task("Servis", LocalDate.of(2020, 12, 10), LocalDate.of(2020, 12, 10), "Blaz Glogovcan", Urgency.LOW, 20));
        this.repairman = new Repairman();
    }

    public void decrementTries() {
        this.numberOfTries--;
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public RepairmanList getrList() {
        return rList;
    }

    public Repairman getRepairman() {
        return repairman;
    }

    public void setRepairman(Repairman repairman) {
        this.repairman = repairman;
    }

    public void addToRList(Repairman repairman) {
        rList.add(repairman);
    }

    public TaskList getTList() {
        return tList;
    }

    public void settList(TaskList tList) {
        this.tList = tList;
    }

    public void addToTList(Task task) {
        tList.add(task);
    }
}
