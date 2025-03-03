package com.example.escape_the_lab;

public abstract class Lab {
    private String name;

    public abstract void startLab();
    public abstract boolean checkSolution();
    public abstract void failLab();
}
