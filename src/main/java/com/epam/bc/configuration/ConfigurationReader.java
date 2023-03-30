package com.epam.bc.configuration;

import org.apache.commons.lang3.math.NumberUtils;

import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j
public class ConfigurationReader {

    private static final Properties properties = new Properties();

    private static ConfigurationReader instance;

    private ConfigurationReader() {
    }

    public static ConfigurationReader getInstance() {
        if ( instance == null ) {
            instance = new ConfigurationReader();
            try (InputStream stream = new FileInputStream("src/main/resources/test.properties")) {
                properties.load(stream);
            }
            catch (IOException ioException) {
                log.error("Properties were not loaded");
            }
        }
        return instance;
    }

    public String getAppPath (){
        return properties.getProperty("app.path");
    }

    public String getAppPackage (){
        return properties.getProperty("app.package");
    }

    public String getAppActivity (){
        return properties.getProperty("app.activity");
    }

    public String getLocalDeviceName (){
        return properties.getProperty("local.device.name");
    }

    public String getUDID (){
        return properties.getProperty("udid");
    }

    public String getAppiumAddress (){
        return properties.getProperty("appium.address");
    }

    public int getAppiumAPort (){
        return NumberUtils.toInt(properties.getProperty("appium.port"));
    }
}
