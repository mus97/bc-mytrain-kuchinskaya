package com.epam.bc.login;

import com.epam.bc.AbstractTest;
import com.epam.bc.pages.MainPage;
import com.epam.bc.pages.ProfilePage;
import com.epam.bc.pages.WelcomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginTest extends AbstractTest {

    private static final String CORRECT_USERNAME = "traddouposauvau-8810@yopmail.com";
    private static final String CORRECT_PASSWORD = "123qazZAQ";
    private static final String INCORRECT_USERNAME = "Incorrect_username";
    private static final String INCORRECT_PASSWORD = "Incorrect_password";
    private static final String EXPECTED_ERROR = "Authorization error";

    @DisplayName("EPMFARMATS-16851 | Test for Login with correct credentials")
    @Test
    void loginPositiveTest() {
        ProfilePage profilePage = new WelcomePage()
                .clickLogin()
                .login(CORRECT_USERNAME, CORRECT_PASSWORD)
                .goThroughWarnings()
                .clickProfileButton();

        softAssertions.assertThat(profilePage.isLogoutButtonVisible()).isTrue();
        softAssertions.assertAll();
    }

    @DisplayName("EPMFARMATS-16850 | Test for Login with incorrect credentials")
    @Test
    void loginNegativeTest() {
        MainPage mainPage = new WelcomePage()
                .clickLogin()
                .login(INCORRECT_USERNAME, INCORRECT_PASSWORD);

        softAssertions.assertThat(mainPage.getErrorMessage()).isEqualTo(EXPECTED_ERROR);
        softAssertions.assertAll();
    }

}
