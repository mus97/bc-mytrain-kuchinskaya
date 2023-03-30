package com.epam.bc.settings;

import com.epam.bc.AbstractTest;
import com.epam.bc.driver.DriverManager;
import com.epam.bc.pages.WelcomePage;
import com.epam.bc.pages.settings.SettingsPage;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

@Log4j
public class ParentSettingsTest extends AbstractTest {

    protected static SettingsPage settingsPage;

    @BeforeAll
    public static void openSettings() {
        settingsPage = new WelcomePage()
                .clickContinueWithoutRegistrationButton()
                .goThroughWarnings()
                .clickMoreButton()
                .openSettings();
    }

    @AfterAll
    public static void resetApp() {
        log.info("End of Test");
        DriverManager.getDriver().resetApp();
    }
}