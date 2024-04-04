package com.mycompany.processproject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessProject {

    public static void main(String[] args) {
        Queue queueQ8 = new Queue(10);
        Queue queueQ16 = new Queue(20);
        Queue queueFCFS = new Queue(30);
        // Act as Long Term Scheduler (LTS)
        ProcessCreator processCreator = new ProcessCreator(queueQ8, 100);
        Thread creatorThread = new Thread(processCreator);
        // Act as Short Term Scheduler (STS)
        ProcessExecuter processExecuter = new ProcessExecuter(queueQ8, queueQ16, queueFCFS, creatorThread);
        Thread executorThread = new Thread(processExecuter);
        
        creatorThread.start();
        executorThread.start();
        
        try {
            creatorThread.join();
            executorThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcessProject.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }
        
        System.out.println(processExecuter.getExcutedProcesses());
    }
}
