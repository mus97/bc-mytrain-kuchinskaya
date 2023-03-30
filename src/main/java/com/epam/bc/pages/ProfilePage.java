package com.epam.bc.pages;

import org.openqa.selenium.By;

public class ProfilePage extends AbstractPage {

    private static final By LOGOUT_BUTTON = By.id("by.rw.client:id/btn_logout");

    public boolean isLogoutButtonVisible() {
        return waitForVisibilityOfElement(LOGOUT_BUTTON).isDisplayed();
    }

}
