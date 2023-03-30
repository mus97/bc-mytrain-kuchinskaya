package com.epam.bc.configuration;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

import static java.lang.String.format;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

@Log4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressConfigurator {

    private static final String ERROR_LOG_LEVEL = "error";
    private static final String KILL_NODE_COMMAND = "taskkill /F /IM node.exe";

    private static AppiumDriverLocalService appiumDriverLocalService;

    public static AppiumDriverLocalService getAppiumDriverLocalService(int port) {
        if (appiumDriverLocalService == null) {
            startService(port);
        }
        return appiumDriverLocalService;
    }

    public static void startService(int port) {
        makePortAvailableIfOccupied(port);
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withIPAddress(ConfigurationReader.getInstance().getAppiumAddress())
                .usingPort(port)
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, ERROR_LOG_LEVEL)
                .build();
        appiumDriverLocalService.start();
        log.info(format("Appium server started on port %s", port));
    }

    public static void stopService() {
        Optional.ofNullable(appiumDriverLocalService).ifPresent(service -> {
            service.stop();
            log.info("Appium server stopped");
        });
    }

    private static void makePortAvailableIfOccupied(int port) {
        if (!isPortFree(port)) {
            try {
                Runtime.getRuntime().exec(KILL_NODE_COMMAND);
                log.info("Execute runtime kill command");
            } catch (IOException e) {
                log.info(format("Couldn't execute runtime command, message %s", e.getMessage()));
            }
        }
    }

    private static boolean isPortFree(int port) {
        boolean isFree = true;
        try (ServerSocket ignored = new ServerSocket(port)) {
            log.info(format("Specified port - %s is available and ready to use", port));
        }
        catch (Exception exception) {
            isFree = false;
            log.info(format("Specified port - %s is occupied by some process, process wil be terminated", port));
        }
        return isFree;
    }
}
