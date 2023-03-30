package com.epam.bc.search;

import com.epam.bc.AbstractTest;
import com.epam.bc.pages.FoundTrainsPage;
import com.epam.bc.pages.SearchPage;
import com.epam.bc.pages.WelcomePage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchTrainTest extends AbstractTest {

    private static SearchPage searchPage;

    @BeforeAll
    public static void openSearch() {
        searchPage = new WelcomePage()
                .clickContinueWithoutRegistrationButton()
                .goThroughWarnings()
                .clickSearchButton();
    }

    @Test
    @DisplayName("EPMFARMATS-16852 | Test for Train Search for today")
    void searchTrainsForTodayTest() {
        String departure = "Minsk";
        String destination = "Brest";
        FoundTrainsPage foundTrainsPage = searchPage.searchTrainsForToday(departure, destination);
        foundTrainsPage.getDirectionText();

        softAssertions.assertThat(foundTrainsPage.getDepartureStation()).isEqualTo(departure);
        softAssertions.assertThat(foundTrainsPage.getDestinationStation()).isEqualTo(destination);
        softAssertions.assertAll();
    }

}
