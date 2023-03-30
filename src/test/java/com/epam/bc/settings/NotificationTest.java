package com.epam.bc.settings;

import com.epam.bc.pages.settings.NotificationsPage;

import lombok.extern.log4j.Log4j;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j
@ExtendWith(SoftAssertionsExtension.class)
public class NotificationTest extends ParentSettingsTest{

    private final static String NOTIFICATION_BLOCK_TEXT = "Android is blocking this app's notifications from appearing on this device";
    private static final String OFF_STATUS = "Off";

    private static NotificationsPage notificationsPage;

    @BeforeAll
    public static void openNotificationPage() {
        notificationsPage = settingsPage
                .openNotifications();
    }

    @Test
    @DisplayName("EPMFARMATS-16823 | Turn off notifications for the application")
    public void blockAppNotificationsTest() {
        notificationsPage
                .turnOffNotifications();
        assertEquals(NOTIFICATION_BLOCK_TEXT, notificationsPage.getBlockedNotificationText(),  "Notification text does not match with expected");
    }

    @Test
    @DisplayName("EPMFARMATS-16823 | Turn off notifications for main / order cancellation / order / payment process categories")
    public void turnOffAppMainNotificationsTest(SoftAssertions softly) {
        notificationsPage
                .turnOnNotifications()
                .turnOffMainAppNotifications()
                .turnOffCancellationNotice()
                .turnOffOrderNotifications()
                .turnOffPaymentProcessNotifications();
        softly.assertThat(OFF_STATUS).isEqualTo(notificationsPage.getAppNotificationsStatusText());
        softly.assertThat(OFF_STATUS).isEqualTo(notificationsPage.getCancellationNoticeStatusText());
        softly.assertThat(OFF_STATUS).isEqualTo(notificationsPage.getOrderNotificationsStatusText());
        softly.assertThat(OFF_STATUS).isEqualTo(notificationsPage.getPaymentProcessStatusText());
    }
}