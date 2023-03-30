package com.epam.bc.cart;

import com.epam.bc.AbstractTest;
import com.epam.bc.pages.FoundTrainsPage;
import com.epam.bc.pages.SearchPage;
import com.epam.bc.pages.WelcomePage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTest extends AbstractTest {

    private static final String USERNAME = "traddouposauvau-8810@yopmail.com";
    private static final String PASSWORD = "123qazZAQ";
    private static final String departure = "Minsk";
    private static final String destination = "Brest";
    private static SearchPage searchPage;

    @BeforeAll
    public static void openSearch() {
        searchPage = new WelcomePage()
                .clickLogin()
                .login(USERNAME, PASSWORD)
                .goThroughWarnings()
                .clickSearchButton();
    }

    @Test
    @DisplayName("EPMFARMATS-16853 | Test adding ticket to cart")
    void cartTest() {
        FoundTrainsPage foundTrainsPage = searchPage.searchTrainsForToday(departure, destination);
        String expectedRoute = foundTrainsPage.getRoute();
        String actualRoute = foundTrainsPage.orderTicket().getOrderedTrainRoute();

        softAssertions.assertThat(actualRoute).isEqualTo(expectedRoute);
        softAssertions.assertAll();
    }
}
