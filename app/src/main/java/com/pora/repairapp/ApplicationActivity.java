package com.pora.repairapp;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import org.apache.commons.io.FileUtils;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pora.lib_repairapp.JobStatus;
import com.pora.lib_repairapp.Repairman;
import com.pora.lib_repairapp.RepairmanList;
import com.pora.lib_repairapp.Task;
import com.pora.lib_repairapp.TaskList;
import com.pora.lib_repairapp.Urgency;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class ApplicationActivity extends Application {
    private static final String MY_TASKS_FILENAME = "tasks.json";
    private static final String MY_WORKERS_FILENAME = "workers.json";
    public static final String CHANNEL_ID = "MyNotifications";
    private int numberOfTries;
    public int myID;
    private RepairmanList rList = new RepairmanList();
    private TaskList tList = new TaskList();
    private Repairman repairman;

    Gson gson;

    File file;
    File fileWorker;

    public static final String APP_ID = "APP_ID_KEY";
    SharedPreferences sp;
    String idAPP;

    public void setAppId() {
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sp.contains(APP_ID)) //READ IT FROM FILE
            idAPP = sp.getString(APP_ID,"DEFAULT VALUE ERR");
        else { //FIRST TIME GENERATE ID AND SAVE IT
            idAPP = UUID.randomUUID().toString().replace("-", "");
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(APP_ID,idAPP);
            editor.apply();
        }
        //list.setUserID(idAPP);
        //Log.d(TAG,"id:"+idAPP);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        setAppId();
        readTasksFromFile();
        readWorkersFromFile();
        this.numberOfTries = 5;
        //this.rList.add(new Repairman("Blaz", "Glogovcan", "blaz.glogovcan@gmail.com", "041 987 626", "blazg", "blaz123", JobStatus.ADMIN));
        //this.rList.add(new Repairman("Martin", "Domajnko", "domajnko.martin@gmail.com", "041 123 456", "martind", "martin123", JobStatus.NORMAL));
        //this.tList.add(new Task("Servis", LocalDate.of(2020, 12, 10), LocalDate.of(2021, 12, 10), "Blaz Glogovcan", Urgency.LOW, 20));
        this.repairman = new Repairman();
        this.myID = 1;
        regNotChannel();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void regNotChannel() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "MyChannel", importance);
        channel.setDescription("My notification test");
        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(channel);
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

    private Gson getGson() {
        if (gson == null)
            gson = new GsonBuilder().setPrettyPrinting().create();
        return gson;
    }

    private File getTasksFile() {
        if (file == null) {
            File filesDir = getFilesDir();
            file = new File(filesDir, MY_TASKS_FILENAME);
        }
        //Log.i(TAG, file.getPath());
        return file;
    }

    private File getWorkersFile() {
        if (fileWorker == null) {
            File filesDir = getFilesDir();
            fileWorker = new File(filesDir, MY_WORKERS_FILENAME);
        }
        //Log.i(TAG, file.getPath());
        return fileWorker;
    }

    private void saveTasksToFile() {
        try {
            FileUtils.writeStringToFile(getTasksFile(), getGson().toJson(tList));
        } catch (IOException e) {
            Log.d("ERROR", "Can't save "+file.getPath());
        }
    }

    private void saveWorkersToFile() {
        try {
            FileUtils.writeStringToFile(getWorkersFile(), getGson().toJson(rList));
        } catch (IOException e) {
            Log.d("ERROR", "Can't save "+file.getPath());
        }
    }

    private boolean readTasksFromFile() {
        if (!getTasksFile().exists())  return false;
        try {
            tList = getGson().fromJson(FileUtils.readFileToString(getTasksFile()), TaskList.class);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private boolean readWorkersFromFile() {
        if (!getWorkersFile().exists())  return false;
        try {
            rList = getGson().fromJson(FileUtils.readFileToString(getWorkersFile()), RepairmanList.class);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void saveTasksData() {
        saveTasksToFile();
    }

    public void saveWorkersData() {
        saveWorkersToFile();
    }
}
