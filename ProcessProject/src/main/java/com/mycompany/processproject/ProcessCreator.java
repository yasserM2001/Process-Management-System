/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.processproject;

/**
 *
 * @author yasser
 */
public class ProcessCreator implements Runnable {

    private final Queue queue;
    private final int numOfProcessesToCreate;

    public ProcessCreator(Queue queue, int numOfProcessesToCreate) {
        this.queue = queue;
        this.numOfProcessesToCreate = numOfProcessesToCreate;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < numOfProcessesToCreate) {
            if (queue.getNumOfProcesses() <= queue.getMaxSize() - 2) {
                Process process = new Process(i + 1);
                queue.enqueue(process);
                i = i + 1;
            } else {
                try {
                Thread.sleep(50); 
            } catch (InterruptedException e) {
            }
            }
        }
    }

}
