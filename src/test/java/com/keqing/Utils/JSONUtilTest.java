package com.keqing.Utils;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class JSONUtilTest {

    @Test
    public void testSaveConfig_createNewFile_saveSuccessfully() {
        String filePath = "test_file.json";

        // Delete the file if it already exists
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            fail("Failed to setup the test fixture");
        }

        JSONUtil jsonUtil = new JSONUtil(filePath);
        jsonUtil.setConfigValue("ServerConfig", "192.168.1.1");

        // Save the configuration to file
        jsonUtil.saveConfig();

        assertTrue(new File(filePath).exists());
    }

    @Test
    public void testSaveConfig_updateExistingFile_saveSuccessfully() {
        String filePath = "test_file2.json";

        // Delete the file if it already exists
        JSONUtil jsonUtil = new JSONUtil(filePath);

        // Add some values to the config
        jsonUtil.setConfigValue("key1", "value1");
        jsonUtil.setConfigValue("key2", "value2");

        // Save the configuration to file
        jsonUtil.saveConfig();

        JSONUtil jsonUtilVerify = new JSONUtil(filePath);

        assertEquals("value1", jsonUtilVerify.getConfigValue("key1"));
        assertEquals("value2", jsonUtilVerify.getConfigValue("key2"));
    }

    @Test
    public void testSaveConfig_withException_handleExceptionGracefully() {
        String invalidFilePath = ".";

        JSONUtil jsonUtil = new JSONUtil(invalidFilePath);

        // Add some values to the config
        jsonUtil.setConfigValue("key1", "value1");

        // Try saving the configuration to file will fail due to the file path being a directory
        // But it should be handled gracefully without throwing exception outside of the method
        jsonUtil.saveConfig();

        // Check that the method did not change the state of the object
        assertEquals("value1", jsonUtil.getConfigValue("key1"));
    }
}