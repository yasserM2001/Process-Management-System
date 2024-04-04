/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.processproject;

import static java.lang.System.exit;
import java.util.ArrayList;

/**
 *
 * @author yasser
 */
public class ProcessExecuter implements Runnable {

    private final Queue queueQ8;
    private final Queue queueQ16;
    private final Queue queueFCFS;
    private final Thread creatorThread;
    private ArrayList<Process> excutedProcesses;

    public ProcessExecuter(Queue queueQ8, Queue queueQ16, Queue queueFCFS, Thread creatorThread) {
        this.queueQ8 = queueQ8;
        this.queueQ16 = queueQ16;
        this.queueFCFS = queueFCFS;
        this.creatorThread = creatorThread;
        this.excutedProcesses = new ArrayList<>();
    }

    private void processQueue(Queue queue, int quantum) {
        Process process = queue.dequeue();
        int remainingBurstTime = process.getBurstTime();

        if (remainingBurstTime < quantum) {
            remainingBurstTime = 0;
            process.setBurstTime(remainingBurstTime);
        } else {
            remainingBurstTime -= quantum;
            process.setBurstTime(remainingBurstTime);
        }

        if (remainingBurstTime > 0) {
            if (quantum == 16) {
                int randomNumber = (int) (Math.random() * 100) + 1;
                // System.out.println("Random Num : " + randomNumber + " Where to Go?");

                if (randomNumber <= 50 && !queueQ8.isFull()) {
                    boolean inserted = queueQ8.enqueue(process);
                    if (!inserted) {
                        queueFCFS.enqueue(process);
                    }
                } else if (randomNumber <= 100 && !queueFCFS.isFull()) {
                    queueFCFS.enqueue(process);
                } else {
                    System.out.println("No Space in Queues");
                    exit(0);
                }
            } else if (quantum == 8) {
                queueQ16.enqueue(process);
            }
        } else {
            System.out.println("Process " + process.getId() + " finished");
            excutedProcesses.add(process);
        }
        // System.out.println("Size of queueQ8: " + queueQ8.getNumOfProcesses());
        // System.out.println("Size of queueQ16: " + queueQ16.getNumOfProcesses());
        // System.out.println("Size of queueFCFS: " + queueFCFS.getNumOfProcesses());
        // System.out.println("*********");

    }

    private void processFCFS(Queue queue) {
        Process process = queue.dequeue();
        process.setBurstTime(0);
        System.out.println("Process " + process.getId() + " finished");
        excutedProcesses.add(process);
    }

    public ArrayList<Process> getExcutedProcesses() {
        return excutedProcesses;
    }

    @Override
    public void run() {
        while (creatorThread.isAlive() || !queueQ8.isEmpty() || !queueQ16.isEmpty() || !queueFCFS.isEmpty()) {
            int randomNumber = (int) (Math.random() * 100) + 1;
            if (randomNumber <= 20 && !queueFCFS.isEmpty()) {
                processFCFS(queueFCFS);
            } else if (randomNumber <= 50 && !queueQ16.isEmpty()) {
                processQueue(queueQ16, 16);
            } else if (!queueQ8.isEmpty() && !queueQ16.isFull()) {
                processQueue(queueQ8, 8);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }

}
