package com.epam.bc.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class WelcomePage extends AbstractPage {

    private static final By CONTINUE_WITHOUT_REGISTRATION_BUTTON = By.id("by.rw.client:id/btn_continue_without_registration");
    private static final By LOGIN_BUTTON = By.id("by.rw.client:id/btn_login");

    public MainPage clickContinueWithoutRegistrationButton() {
        log.info("Click continue without registration button");
        waitForVisibilityOfElement(CONTINUE_WITHOUT_REGISTRATION_BUTTON).click();
        return new MainPage();
    }

    public LoginPage clickLogin(){
        waitForVisibilityOfElement(LOGIN_BUTTON).click();
        return new LoginPage();
    }
}