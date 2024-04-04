/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.processproject;

/**
 *
 * @author yasser
 */
public class Queue {
    private Node head;
    private Node tail;
    private int numOfProcesses;
    private final int maxSize;

    public Queue(int maxSize) {
        this.head = null;
        this.tail = null;
        this.numOfProcesses= 0;
        this.maxSize = maxSize;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

     public boolean isFull() {
        return numOfProcesses == maxSize;
    }

    public synchronized boolean enqueue(Process process) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue.");
            return false;
        }

        Node newNode = new Node(process);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }

        numOfProcesses++;
        return true;
    }

    public synchronized Process dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }

        Process dequeuedProcess = head.getProcess();
        head = head.getNext();

        if (head == null) {
            tail = null;
        }

        numOfProcesses--;

        return dequeuedProcess;
    }

    public int getNumOfProcesses() {
        return numOfProcesses;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void printQueue() {
        Node current = head;
        while (current != null) {
            Process process = current.getProcess();
            System.out.println("Process " + process.getId() + " - Burst Time: " + process.getBurstTime());
            current = current.getNext();
        }
    }
}
