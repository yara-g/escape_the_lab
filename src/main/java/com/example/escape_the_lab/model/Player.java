package com.example.escape_the_lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable {
    boolean sound;
    String language;
    boolean autosave;
    String username, password;
    int lives, level;

    @JsonIgnore
    ObjectMapper objectMapper = new ObjectMapper();
    @JsonIgnore
    File file;

    public Player(boolean sound, String language, boolean autosave, String username, String password, int lives, int level, String filePath) {
        this.sound = sound;
        this.language = language;
        this.autosave = autosave;
        this.username = username;
        this.password = password;
        this.lives = lives;
        this.level = level;
        file = new File(filePath);
        writeToJSON();
    }

    private void writeToJSON() {
        try {
            String json = objectMapper.writeValueAsString(this);
            FileWriter fw = new FileWriter(file);
            fw.write(json);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // getters and setters below
    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean isSoundOn() {
        return sound;
    }

    public void setAutosave(boolean autosave) {
        this.autosave = autosave;
    }

    public boolean isAutosave() {
        return autosave;
    }

    public void setLanguage(String language) {
        this.language = language;
        writeToJSON();
    }

    public int getLevel() {
        return level;
    }

    public int getLives() {
        return lives;
    }

    public String getLanguage() {
        return language;
    }

    public String getPassword() {
        return password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

