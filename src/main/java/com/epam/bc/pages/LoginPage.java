package com.epam.bc.pages;

import org.openqa.selenium.By;

public class LoginPage extends AbstractPage{

    private static final By USERNAME_FIELD = By.id("by.rw.client:id/login_edit_text");
    private static final By PASSWORD_FIELD = By.id("by.rw.client:id/password_edit_text");
    private static final By LOGIN_BUTTON = By.id("by.rw.client:id/button_login");

    public MainPage login (String username, String password){
        waitForVisibilityOfElement(USERNAME_FIELD).sendKeys(username);
        waitForVisibilityOfElement(PASSWORD_FIELD).sendKeys(password);
        waitForVisibilityOfElement(LOGIN_BUTTON).click();
        return new MainPage();
    }
}
