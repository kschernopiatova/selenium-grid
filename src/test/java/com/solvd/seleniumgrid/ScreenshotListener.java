package com.solvd.seleniumgrid;

import com.solvd.seleniumgrid.web.service.ConfigService;
import com.solvd.seleniumgrid.web.util.DriverUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class ScreenshotListener implements ITestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getName().trim();
        WebDriver driver = DriverUtil.getDriver(ConfigService.getValue("driver_name"));
        takeScreenShot(methodName, driver);
    }

    public void takeScreenShot(String methodName, WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(scrFile, new File("screenshots/" + methodName + ".png"));
            LOGGER.info("Screenshot is placed into screenshots directory");
        } catch (IOException e) {
            LOGGER.warn("Unable to take screenshot");
        }
    }
}
