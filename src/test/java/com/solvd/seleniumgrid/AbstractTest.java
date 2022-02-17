package com.solvd.seleniumgrid;

import com.solvd.seleniumgrid.web.service.ConfigService;
import com.solvd.seleniumgrid.web.service.TestDataService;
import com.solvd.seleniumgrid.web.util.DriverUtil;
import org.testng.annotations.*;

public abstract class AbstractTest {

    @BeforeSuite
    public void beforeSuite() {
        ConfigService.createInstance();
        TestDataService.createInstance();
    }

    @AfterTest
    public void afterTests() {
        DriverUtil.releaseDrivers();
    }
}
