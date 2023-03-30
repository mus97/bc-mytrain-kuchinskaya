package com.epam.bc.listener;

import com.epam.bc.driver.DriverManager;
import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.message.TypeAwareByteSource;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.reportportal.utils.MimeTypeDetector;

import com.google.common.io.Files;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static java.lang.String.format;

@Log4j
public class ReportPortalTestNgListener extends ReportPortalTestNGListener {

    @Override
    public void onTestFailure(ITestResult result) {
        File screenCapture = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        logErrorScreenshotToRP(screenCapture);
        super.onTestFailure(result);
    }

    public static void logErrorScreenshotToRP(File screenshotFile) {
        if (screenshotFile != null) {
            try {
                TypeAwareByteSource typeAwareByteSource = new TypeAwareByteSource(Files.asByteSource(Objects.requireNonNull(screenshotFile)),
                                                                                  MimeTypeDetector.detect(screenshotFile));
                log.error(new ReportPortalMessage(typeAwareByteSource, format(" -> Mobiledriver screenshot captured: %s", screenshotFile.getName())));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
