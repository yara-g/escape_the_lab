package com.example.escape_the_lab.model;

import javafx.scene.Scene;

public abstract class Lab {
    private String name;

    public abstract void startLab();
    public abstract boolean checkSolution();
    public abstract void failLab();
    public abstract void setupLab();
    public abstract Scene createScene();
}
