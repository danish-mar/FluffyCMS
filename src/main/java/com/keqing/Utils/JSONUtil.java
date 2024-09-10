package com.keqing.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class to manage JSON configuration stored in a file.
 * The class provides methods to load, save, and manipulate configurations in JSON format.
 */
public class JSONUtil {

    private JSONObject config;
    private final File configFile;

    // Constructor to initialize with a config file
    public JSONUtil(String filePath) {
        this.configFile = new File(filePath);
        if (configFile.exists() && !configFile.isDirectory()) {
            loadConfig();
        } else {
            config = new JSONObject();
        }
    }

    // Method to load the config from the file
    private void loadConfig() {
        try (FileReader reader = new FileReader(configFile)) {
            char[] buffer = new char[(int) configFile.length()];
            reader.read(buffer);
            String content = new String(buffer);
            config = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            config = new JSONObject();
        }
    }

    // Method to save the current config to the file
    public void saveConfig() {
        try {
            if (configFile.isDirectory()) {
                System.err.println("Cannot write to a directory. Please provide a valid file path.");
                return;
            }
            try (FileWriter writer = new FileWriter(configFile)) {
                writer.write(config.toString(4));  // Pretty print with indentation
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get a value from the config
    public Object getConfigValue(String key) {
        return config.opt(key);
    }

    // Method to set a value in the config
    public void setConfigValue(String key, Object value) {
        config.put(key, value);
    }

    // Method to remove a key from the config
    public void removeConfigValue(String key) {
        config.remove(key);
    }

    // Check if the config contains a specific key
    public boolean containsKey(String key) {
        return config.has(key);
    }

    // Method to reload the config from the file
    public void reloadConfig() {
        loadConfig();
    }

    // Method to clear all configurations
    public void clearConfig() {
        config = new JSONObject();
    }

    // New: Method to store an array (string array)
    public void setStringArray(String arrayName, String[] elements) {
        JSONArray jsonArray = new JSONArray();
        for (String element : elements) {
            jsonArray.put(element);
        }
        config.put(arrayName, jsonArray);
    }

    // New: Method to retrieve a string array from the config
    public String[] getStringArray(String arrayName) {
        JSONArray jsonArray = config.optJSONArray(arrayName);
        if (jsonArray == null) {
            return new String[0];  // Return empty array if not found
        }

        String[] result = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            result[i] = jsonArray.getString(i);
        }
        return result;
    }

    // New: Method to append a value to an existing string array
    public void appendToStringArray(String arrayName, String newValue) {
        JSONArray jsonArray = config.optJSONArray(arrayName);
        if (jsonArray == null) {
            jsonArray = new JSONArray();
        }
        jsonArray.put(newValue);
        config.put(arrayName, jsonArray);
    }

    // New: Method to remove a specific value from an array
    public void removeFromStringArray(String arrayName, String valueToRemove) {
        JSONArray jsonArray = config.optJSONArray(arrayName);
        if (jsonArray != null) {
            JSONArray newArray = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                String value = jsonArray.getString(i);
                if (!value.equals(valueToRemove)) {
                    newArray.put(value);
                }
            }
            config.put(arrayName, newArray);
        }
    }
}