package com.epam.bc.pages.settings;

import com.epam.bc.pages.AbstractPage;

import io.appium.java_client.MobileElement;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.By;

import java.util.List;

import static java.lang.String.format;

@Log4j
public class SettingsPage extends AbstractPage {

    private static final By REGION_LAYOUT = By.id("by.rw.client:id/setting_region_layout");
    private static final By NOTIFICATIONS_LAYOUT = By.id("by.rw.client:id/setting_push_layout");
    private static final By REGION_CLASS = By.className("android.widget.CheckedTextView");
    private static final By REGION_LABEL = By.id("by.rw.client:id/region_label");

    public SettingsPage openRegionLayout() {
        log.info("Open region layout");
        waitForVisibilityOfElement(REGION_LAYOUT).click();
        return this;
    }

    public void selectRegion(String region) {
        log.info(format("Select region as %s", region));
        List<MobileElement> regionsList = driver.findElements(REGION_CLASS);
        for (MobileElement element : regionsList) {
            if (element.getText().equals(region)) {
                element.click();
                break;
            }
        }
    }

    public String getRegionLabel() {
        log.info("Get region label");
        return waitForVisibilityOfElement(REGION_LABEL).getText();
    }

    public NotificationsPage openNotifications() {
        log.info("Open notification layout");
        waitForVisibilityOfElement(NOTIFICATIONS_LAYOUT).click();
        return new NotificationsPage();
    }
}