package com.epam.bc.pages.settings;

import com.epam.bc.pages.AbstractPage;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.By;

import static java.lang.String.format;

@Log4j
public class NotificationsPage extends AbstractPage {

    private static final By NOTIFICATION_SWITCH = By.id("com.android.settings:id/switch_widget");
    private static final By NOTIFICATION_STATUS = By.id("com.android.settings:id/switch_text");
    private static final By BLOCKED_NOTIFICATION_TEXT = By.id("android:id/title");
    private static final String BASE_LOCATOR_FOR_SWITCH = "//android.widget.Switch[@content-desc='%s']";
    private static final String BASE_LOCATOR_FOR_STATUS_TEXT = "//android.widget.Switch[@content-desc = '%s']/../..//android.widget.TextView[@resource-id='android:id/summary']";
    private static final String APP_NOTIFICATIONS = "My train application notifications";
    private static final String CANCELLATION_NOTIFICATIONS = "Order Cancellation Notice";
    private static final String ORDER_NOTIFICATIONS = "Order Notification";
    private static final String PAYMENT_PROCESS_NOTIFICATIONS = "Payment process";
    private static final String APP_NOTIFICATIONS_STATUS_ON = "Make sound";
    private static final String MAKE_SOUND_STATUS = "Make sound and pop on screen";
    private static final String NO_SOUND_STATUS = "No sound or visual interruption";
    private static final String STATUS_ON = "On";
    private static final String STATUS_OFF = "Off";

    public NotificationsPage turnOnNotifications() {
        log.info("Turn Notification On");
        toggleNotificationSwitchTo(STATUS_ON);
        return this;
    }

    public void turnOffNotifications() {
        log.info("Turn Notification Off");
        toggleNotificationSwitchTo(STATUS_OFF);
    }

    public String getBlockedNotificationText() {
        log.info("Get blocked notification text");
        return driver.findElement(BLOCKED_NOTIFICATION_TEXT).getText();
    }

    public NotificationsPage turnOffMainAppNotifications() {
        log.info(format("Turn %s Off", APP_NOTIFICATIONS));
        toggleSwitch(APP_NOTIFICATIONS, APP_NOTIFICATIONS_STATUS_ON);
        return this;
    }

    public NotificationsPage turnOffCancellationNotice() {
        log.info(format("Turn %s Off", CANCELLATION_NOTIFICATIONS));
        toggleSwitch(CANCELLATION_NOTIFICATIONS, MAKE_SOUND_STATUS);
        return this;
    }

    public NotificationsPage turnOffOrderNotifications() {
        log.info(format("Turn %s Off", ORDER_NOTIFICATIONS));
        toggleSwitch(ORDER_NOTIFICATIONS, MAKE_SOUND_STATUS);
        return this;
    }

    public void turnOffPaymentProcessNotifications() {
        log.info(format("Turn %s Off", PAYMENT_PROCESS_NOTIFICATIONS));
        toggleSwitch(PAYMENT_PROCESS_NOTIFICATIONS, NO_SOUND_STATUS);
    }

    public String getAppNotificationsStatusText() {
        log.info("Get App notification status text");
        return getElementTextByXpath(APP_NOTIFICATIONS);
    }

    public String getCancellationNoticeStatusText() {
        log.info("Get Cancellation notice status text");
        return getElementTextByXpath(CANCELLATION_NOTIFICATIONS);
    }

    public String getOrderNotificationsStatusText() {
        log.info("Get Order notification status text");
        return getElementTextByXpath(ORDER_NOTIFICATIONS);
    }

    public String getPaymentProcessStatusText() {
        log.info("Get Payment process status text");
        return getElementTextByXpath(PAYMENT_PROCESS_NOTIFICATIONS);
    }

    private String getElementTextByXpath(String element) {
        log.info(format("Get %s text", element));
        return driver.findElementByXPath(format(BASE_LOCATOR_FOR_STATUS_TEXT, element)).getText();
    }

    private void toggleSwitch(String element, String status) {
        log.info(format("Toggle switch %s to status %s", element, status));
        if (waitForVisibilityOfElement(By.xpath(format(BASE_LOCATOR_FOR_STATUS_TEXT, element))).getText().equals(status)) {
            driver.findElementByXPath(format(BASE_LOCATOR_FOR_SWITCH, element)).click();
        }
    }

    private void toggleNotificationSwitchTo(String status) {
        log.info(format("Toggle Notification switch to status %s", status));
        if (!waitForVisibilityOfElement(NOTIFICATION_STATUS).getText().equals(status)) {
            driver.findElement(NOTIFICATION_SWITCH).click();
        }
    }
}