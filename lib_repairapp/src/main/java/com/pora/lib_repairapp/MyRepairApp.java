package com.pora.lib_repairapp;

import java.sql.SQLOutput;
import java.time.LocalDate;

public class MyRepairApp {
    public static void main(String[] args) {
        Repairman blaz = new Repairman("Blaz", "Glogovcan", "blaz.glogovcan@gmail.com", "051753888", "blazg", "blaz123", JobStatus.ADMIN);
        Repairman martin = new Repairman("Martin", "Domajnko", "domajnko.martin@gmail.com", "051753889", "martind", "martin123", JobStatus.NORMAL);

        /*Task popravilo1 = new Task("Popravilo stroja 1", LocalDate.of(2020, 12, 25), LocalDate.now(), blaz, Urgency.MEDIUM);
        Task popravilo2 = new Task("Popravilo stroja 255", LocalDate.of(2021, 8, 15), LocalDate.now(), blaz, Urgency.LOW);*/

        TaskList taskList = new TaskList();
        /*taskList.add(popravilo1);
        taskList.add(popravilo2);*/

        RepairmanList repList = new RepairmanList();
        repList.add(blaz);
        repList.add(martin);

        /*System.out.println(taskList.toString());*/
        System.out.println(repList.toString());

        Repairman repairman = repList.getRepairman("blazg");
        System.out.println(repairman.toString());
        System.out.println(blaz.getFirstName());
    }
}
