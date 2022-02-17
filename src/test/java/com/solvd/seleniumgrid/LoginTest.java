package com.solvd.seleniumgrid;

import com.solvd.seleniumgrid.web.pages.AccountPage;
import com.solvd.seleniumgrid.web.service.ConfigService;
import com.solvd.seleniumgrid.web.service.LoginService;
import com.solvd.seleniumgrid.web.service.TestDataService;
import com.solvd.seleniumgrid.web.util.DriverUtil;
import com.solvd.seleniumgrid.web.util.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class LoginTest extends AbstractTest {

    private static LoginService loginService;
    public static WebDriver driver;

    @BeforeClass
    public void before() {
        driver = DriverUtil.getDriver(ConfigService.getValue("driver_name"));
        loginService = new LoginService(driver);
    }

    @Test
    public void invalidEmailTest() {
        String email = TestDataService.getValue("email");
        String pass = TestDataService.getValue("password");
        AccountPage accountPage = loginService.login(email, pass);
        WaitUtil.sleep(5);
        Assert.assertTrue(accountPage.getTitle().isDisplayed(), "Account page is not opened");
    }
}
