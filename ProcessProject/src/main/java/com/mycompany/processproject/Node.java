/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.processproject;

/**
 *
 * @author yasser
 */
public class Node {
    private final Process process;
    private Node next;

    public Node(Process process) {
        this.process = process;
        this.next = null;
    }

    public Process getProcess() {
        return process;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
