package com.epam.bc.pages;

import com.epam.bc.driver.DriverManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected static final int BASE_WAIT_TIME = 10;
    protected static final int LONG_WAIT_TIME = 60;

    public AppiumDriver<MobileElement> driver;

    public AbstractPage() {
        this(DriverManager.getDriver());
    }

    public AbstractPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(
                DriverManager.getDriver()), this);
    }

    public WebElement waitForVisibilityOfElement(By locator) {
        return new WebDriverWait(driver, BASE_WAIT_TIME)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement longWaitForVisibilityOfElement(By locator) {
        return new WebDriverWait(driver, LONG_WAIT_TIME)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}