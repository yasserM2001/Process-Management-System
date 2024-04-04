/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.processproject;

import java.util.Random;

/**
 *
 * @author yasser
 */
public class Process {
    private int id;
    private int burstTime;

    public Process(int id) {
        this.id = id;
        Random random = new Random();
        this.burstTime = random.nextInt(100) + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    @Override
    public String toString() {
        return "Process id = " + id + ", burstTime = " + burstTime;
    }
    
    
}
