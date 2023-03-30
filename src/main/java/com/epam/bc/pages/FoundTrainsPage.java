package com.epam.bc.pages;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class FoundTrainsPage extends AbstractPage {

    private static final By DIRECTION_FIELD = By.id("by.rw.client:id/text_timetable_route_title");
    private static final By AVAILABLE_TICKETS = By.id("by.rw.client:id/iv_seat_price");
    private static final By AVAILABLE_CAR = By.id("by.rw.client:id/ll_list_item_car_color");
    private static final By CONTINUE_WO_SEATS_SELECTION = By.id("by.rw.client:id/action_button");
    private static final String CONFIRMATION_BUTTON = "by.rw.client:id/switch_place_order";
    private static final By SUBMIT_BUTTON = By.id("by.rw.client:id/btn_place_order");
    private static final By FILL_PASSENGER = By.id("by.rw.client:id/tv_list_item_passenger_data_fill");
    private static final By SELECT_PASSENGER = By.id("by.rw.client:id/passenger_name");
    private static final By TRAIN_ROUTE = By.id("by.rw.client:id/tv_timetable_item_train_name");
    private String[] split;

    public void getDirectionText() {
        log.info("Get direction text");
        waitForVisibilityOfElement(DIRECTION_FIELD);
        String stations = driver.findElement(DIRECTION_FIELD).getText();
        split = stations.split(" . ");
    }

    public String getDepartureStation() {
        return split[0];
    }

    public String getDestinationStation() {
        return split[1];
    }

    public String getRoute() {
        String searchedTainRoute = longWaitForVisibilityOfElement(TRAIN_ROUTE).getText();
        log.info("The route of searched train: " + searchedTainRoute);
        return searchedTainRoute;
    }

    public void selectTicket() {
        waitForVisibilityOfElement(AVAILABLE_TICKETS).click();
    }

    public void selectCar() {
        waitForVisibilityOfElement(AVAILABLE_CAR).click();
    }

    public void continueWoSeatsSelection() {
        waitForVisibilityOfElement(CONTINUE_WO_SEATS_SELECTION).click();
    }

    public void fillPassenger() {
        waitForVisibilityOfElement(FILL_PASSENGER).click();
    }

    public void selectPassenger() {
        waitForVisibilityOfElement(SELECT_PASSENGER).click();
    }

    public void clickConfirmation() {
        ((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".scrollIntoView(new UiSelector().resourceId(\"" + CONFIRMATION_BUTTON + "\").instance(0))");
        waitForVisibilityOfElement(By.id(CONFIRMATION_BUTTON)).click();
    }

    public void clickSubmit() {
        waitForVisibilityOfElement(SUBMIT_BUTTON).click();
    }

    public CartPage orderTicket() {
        selectTicket();
        selectCar();
        continueWoSeatsSelection();
        fillPassenger();
        selectPassenger();
        clickConfirmation();
        clickSubmit();
        return new CartPage();
    }
}
