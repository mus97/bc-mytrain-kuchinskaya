package com.epam.bc.settings;

import lombok.extern.log4j.Log4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j
public class RegionTest extends ParentSettingsTest {

    @ParameterizedTest
    @ValueSource (strings = {"Brest", "Homiel", "Hrodna"})
    @DisplayName("EPMFARMATS-16823 | Change region")
    public void regionTest(String region) {
        settingsPage.openRegionLayout().selectRegion(region);
        assertEquals(settingsPage.getRegionLabel(), region, "Region has not been changed");
    }
}