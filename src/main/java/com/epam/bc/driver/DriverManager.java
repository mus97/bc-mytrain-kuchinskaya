package com.epam.bc.driver;

import com.epam.bc.configuration.AddressConfigurator;
import com.epam.bc.configuration.CapabilitiesConfigurator;
import com.epam.bc.configuration.ConfigurationReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

@Log4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverManager {

    private static AppiumDriver<MobileElement> driver;

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private static AppiumDriver<MobileElement> createDriver() {
        log.info("Driver is created");
        return new AndroidDriver<>
                (AddressConfigurator.getAppiumDriverLocalService
                        (ConfigurationReader.getInstance().getAppiumAPort()),
                        CapabilitiesConfigurator.getLocalCapabilities());
    }

    public static void closeDriver() {
        Optional.ofNullable(getDriver()).ifPresent(driverInstance -> {
            driverInstance.quit();
            driver = null;
            log.info("Driver is closed");
        });
    }

    public static void closeAppium() {
        AddressConfigurator.stopService();
    }

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("adb -s %s emu kill", ConfigurationReader.getInstance().getUDID()));
            log.info("Emulator is closed");
        }
        catch (IOException ioException) {
            log.info(format("Emulator was not closing, message %s", ioException.getMessage()));
        }
    }
}