package com.pora.lib_repairapp;

import java.util.ArrayList;

public class RepairmanList implements Sizable {
    private ArrayList<Repairman> repairmen;

    public RepairmanList() {
        this.repairmen = new ArrayList<>();
    }

    public void add(Repairman repairman) {
        repairmen.add(repairman);
    }

    @Override
    public int size() {
        return repairmen.size();
    }

    @Override
    public String toString() {
        return "RepairmanList{" +
                "repairmen=" + repairmen.toString() +
                '}';
    }

    public Repairman getRepairman(String username) {
        for(Repairman repairman : repairmen) {
            if(repairman.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return repairman;
            }
        }
        return null;
    }

    public boolean usernameAlreadyExists(String username) {
        for(Repairman repairman : repairmen) {
            if(repairman.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean emailAlreadyExists(String email) {
        for(Repairman repairman : repairmen) {
            if(repairman.getEmailAddress().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Repairman getRepairmanByPosition(int position) {
        return repairmen.get(position);
    }

    public ArrayList<Repairman> getRepairmen() {
        return repairmen;
    }
}
