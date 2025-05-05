package escape_the_lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable {
    boolean sound;
    String language;
    String username, password;

    @JsonIgnore
    ObjectMapper objectMapper = new ObjectMapper();
    @JsonIgnore
    File file;

    // make new player
    public Player(boolean sound, String language, String username, String password, String filePath) {
        this.sound = sound;
        this.language = language;
        this.username = username;
        this.password = password;
        file = new File(filePath);
        writeToJSON();
    }

    // write data to the json file of player
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

    // use the last player by reading the json file (as opposed to making a new one)
    public static Player getLastPlayer() {
        boolean sound;
        String language;
        String username, password;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/json/jane-doe.json"));
            sound = jsonNode.get("soundOn").asBoolean();
            language = jsonNode.get("language").asText();
            username = jsonNode.get("username").asText();
            password = jsonNode.get("password").asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Player(sound, language, username, password, "src/main/resources/json/jane-doe.json");
    }

    // getters and setters below
    public void setSound(boolean sound) {
        this.sound = sound;
        writeToJSON();
    }

    public boolean isSoundOn() {
        return sound;
    }

    public void setLanguage(String language) {
        this.language = language;
        writeToJSON();
    }

    public String getLanguage() {
        return language;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
        writeToJSON();
    }

    public void setUsername(String username) {
        this.username = username;
        writeToJSON();
    }
}

