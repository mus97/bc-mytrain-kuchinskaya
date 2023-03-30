package com.epam.bc.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class CartPage extends AbstractPage{

    private static final By ORDERED_TRAIN_ROUTE = By.id("by.rw.client:id/text_train_name");

    public String getOrderedTrainRoute(){
        String orderedTainRoute = waitForVisibilityOfElement(ORDERED_TRAIN_ROUTE).getText();
        log.info("The route of ordered train: " + orderedTainRoute);
        return orderedTainRoute;
    }
}
