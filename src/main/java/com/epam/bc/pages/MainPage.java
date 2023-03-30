package com.epam.bc.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class MainPage extends AbstractPage {

    private static final By SEARCH_BUTTON = By.id("by.rw.client:id/btn_find_timetable");
    private static final By MORE_BUTTON = By.id("by.rw.client:id/navigation_more");
    private static final By LOCATION_DETERMINATION_OK_BUTTON = By.id("by.rw.client:id/button_ok");
    private static final By PERMISSION_DENY_BUTTON = By.id("com.android.packageinstaller:id/permission_deny_button");
    private static final By DEFAULT_LOCATION_OK_BUTTON = By.id("by.rw.client:id/button_ok");
    private static final By PROFILE_BUTTON = By.id("by.rw.client:id/navigation_profile");
    private static final By AUTHORIZATION_ERROR = By.id("by.rw.client:id/text_title");

    public MorePage clickMoreButton() {
        waitForVisibilityOfElement(MORE_BUTTON).click();
        return new MorePage();
    }

    public SearchPage clickSearchButton() {
        waitForVisibilityOfElement(SEARCH_BUTTON).click();
        return new SearchPage();
    }

    public ProfilePage clickProfileButton() {
        log.info("Click Profile button");
        waitForVisibilityOfElement(PROFILE_BUTTON).click();
        return new ProfilePage();
    }

    public String getErrorMessage() {
        return waitForVisibilityOfElement(AUTHORIZATION_ERROR).getText();
    }

    public void clickLocationDeterminationOkButton() {
        log.info("Click determination OK button");
        longWaitForVisibilityOfElement(LOCATION_DETERMINATION_OK_BUTTON).click();
    }

    public void clickPermossionDenyButton() {
        log.info("Click permission deny button");
        waitForVisibilityOfElement(PERMISSION_DENY_BUTTON).click();
    }

    public void clickDefaultLocationOkButton() {
        log.info("Click default location OK button");
        waitForVisibilityOfElement(DEFAULT_LOCATION_OK_BUTTON).click();
    }

    public MainPage goThroughWarnings() {
        clickLocationDeterminationOkButton();
        clickPermossionDenyButton();
        clickDefaultLocationOkButton();
        return new MainPage();
    }
}