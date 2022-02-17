package com.solvd.seleniumgrid.grid;

import com.solvd.seleniumgrid.AbstractTest;
import com.solvd.seleniumgrid.ScreenshotListener;
import com.solvd.seleniumgrid.web.pages.AccountPage;
import com.solvd.seleniumgrid.web.service.ConfigData;
import com.solvd.seleniumgrid.web.service.ConfigService;
import com.solvd.seleniumgrid.web.service.LoginService;
import com.solvd.seleniumgrid.web.service.TestDataService;
import com.solvd.seleniumgrid.web.util.WaitUtil;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(ScreenshotListener.class)
public class LoginTest extends AbstractTest {

    private LoginService loginService;
    protected static WebDriver driver;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        String browser = ConfigService.getValue(ConfigData.BROWSER);
        String url = ConfigService.getValue(ConfigData.SELENIUM_URL);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.MAC);
        capabilities.setBrowserName(browser);
        driver = new RemoteWebDriver(new URL(url), capabilities);
        loginService = new LoginService(driver);
    }

    @Test
    public void loginTest() {
        String email = TestDataService.getValue("email");
        String pass = TestDataService.getValue("password");
        AccountPage accountPage = loginService.login(email, pass);
        WaitUtil.waitVisibility(driver, accountPage.getTitle());
        Assert.assertTrue(accountPage.getTitle().isDisplayed(), "Account page is not opened");
        driver.quit();
    }
}
