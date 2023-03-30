package com.epam.bc.pages;

import com.epam.bc.pages.settings.SettingsPage;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.By;

@Log4j
public class MorePage extends AbstractPage{

    private static final By SETTINGS_BUTTON = By.id("by.rw.client:id/more_settings");

    public SettingsPage openSettings() {
        log.info("Open Settings page");
        waitForVisibilityOfElement(SETTINGS_BUTTON).click();
        return new SettingsPage();
    }
}
