package com.epam.bc.configuration;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;

public class CapabilitiesConfigurator {

    private CapabilitiesConfigurator() {
    }

    public static DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(UDID, ConfigurationReader.getInstance().getUDID());
        capabilities.setCapability(AVD, ConfigurationReader.getInstance().getLocalDeviceName());
        capabilities.setCapability(APP_PACKAGE, ConfigurationReader.getInstance().getAppPackage());
        capabilities.setCapability(APP_ACTIVITY, ConfigurationReader.getInstance().getAppActivity());
        capabilities.setCapability(APP, new File(ConfigurationReader.getInstance().getAppPath()).getAbsolutePath());
        return capabilities;
    }
}
