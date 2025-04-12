package com.example.escape_the_lab.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;

public class JSONManager {
    File jsonFile;
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode;
    ObjectNode objectNode;

    boolean sound;
    String language;
    boolean autosave;
    String username, password;
    int lives, level;

    public static void main(String[] args) {
        JSONManager jsonManager = new JSONManager("/json/jane-doe.json");
        jsonManager.setSound();
    }

    public JSONManager(String jsonPath) {
        initJSON();
        jsonFile = new File(jsonPath);

        sound = jsonNode.get("sound").asBoolean();
        language = jsonNode.get("language").asText();
        autosave = jsonNode.get("autosave").asBoolean();
        username = jsonNode.get("username").asText();
        password = jsonNode.get("password").asText();
        lives = jsonNode.get("lives").asInt();
        level = jsonNode.get("level").asInt();
    }

    public void setSound() {
        objectNode.put("WORK", false);
        try {
            objectMapper.writeValue(jsonFile, objectNode.get(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSoundOn() {
        return sound;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isAutosave() {
        return autosave;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    private void initJSON() {
        try {
            jsonNode = objectMapper.readTree(jsonFile);
            ObjectNode objectNode = objectMapper.readValue((DataInput) jsonNode, ObjectNode.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
