package com.epam.bc;

import com.epam.bc.driver.DriverManager;

import lombok.extern.log4j.Log4j;

import org.junit.jupiter.api.AfterAll;

import org.assertj.core.api.SoftAssertions;

@Log4j
public abstract class AbstractTest {

    protected SoftAssertions softAssertions = new SoftAssertions();

//    @AfterAll
//    public static void closeSession() {
//        log.info("End of All Tests");
//        DriverManager.closeDriver();
//        DriverManager.closeAppium();
//        DriverManager.closeEmulator();
//    }
}