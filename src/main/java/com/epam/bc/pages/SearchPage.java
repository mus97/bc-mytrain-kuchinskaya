package com.epam.bc.pages;

import io.appium.java_client.MobileElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

import java.util.List;

import static java.lang.String.format;

@Log4j
public class SearchPage extends AbstractPage {
    private static final By FROM_BUTTON = By.id("by.rw.client:id/departure_station_selection");
    private static final By SEARCH_FIELD = By.id("by.rw.client:id/search_src_text");
    private static final By TO_BUTTON = By.id("by.rw.client:id/destination_station_selection_container");
    private static final By DISPLAYED_CITIES = By.id("by.rw.client:id/tv_station_name");
    private static final By SEARCH_BUTTON = By.id("by.rw.client:id/btn_find_timetable");

    public void inputDeparture(String departure) {
        log.info(format("Input departure city: %s", departure));
        waitForVisibilityOfElement(FROM_BUTTON).click();
        waitForVisibilityOfElement(SEARCH_FIELD).sendKeys(departure);
        chooseCity(departure);
    }

    public void inputDestination(String destination) {
        log.info(format("Input destination city: %s", destination));
        waitForVisibilityOfElement(TO_BUTTON).click();
        waitForVisibilityOfElement(SEARCH_FIELD).sendKeys(destination);
        chooseCity(destination);
    }

    public void chooseCity(String city) {
        List<MobileElement> citiesList = driver.findElements(DISPLAYED_CITIES);
        for (MobileElement element : citiesList) {
            if (element.getText().equals(city)) {
                element.click();
                break;
            }
        }
    }

    public void clickSearchButton() {
        log.info("Click Search.");
        waitForVisibilityOfElement(SEARCH_BUTTON).click();
    }

    public FoundTrainsPage searchTrainsForToday(String departure, String destination) {
        log.info("Searching train for today.");
        inputDeparture(departure);
        inputDestination(destination);
        clickSearchButton();
        return new FoundTrainsPage();
    }

}
