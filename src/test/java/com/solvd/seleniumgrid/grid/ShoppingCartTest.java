package com.solvd.seleniumgrid.grid;

import com.solvd.seleniumgrid.AbstractTest;
import com.solvd.seleniumgrid.ScreenshotListener;
import com.solvd.seleniumgrid.web.pages.ShoppingCartPage;
import com.solvd.seleniumgrid.web.service.ConfigData;
import com.solvd.seleniumgrid.web.service.ConfigService;
import com.solvd.seleniumgrid.web.service.ProductPageService;
import com.solvd.seleniumgrid.web.service.TestDataService;
import com.solvd.seleniumgrid.web.util.WaitUtil;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(ScreenshotListener.class)
public class ShoppingCartTest extends AbstractTest {

    protected static WebDriver driver;
    private ProductPageService productPageService;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        String browser = ConfigService.getValue(ConfigData.BROWSER);
        String url = ConfigService.getValue(ConfigData.SELENIUM_URL);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.MAC);
        capabilities.setBrowserName(browser);
        driver = new RemoteWebDriver(new URL(url), capabilities);
        productPageService = new ProductPageService(driver);
    }

    @Test
    public void addToBagTest() {
        productPageService.openProductPage();
        String productTitle = productPageService.getProductTitleText();
        ShoppingCartPage shoppingCartPage =
                productPageService.addProductToBag(TestDataService.getValue("size"));
        WaitUtil.waitVisibility(driver, shoppingCartPage.getTitle());
        Assert.assertTrue(shoppingCartPage.getTitle().isDisplayed(), "Cart isn't opened");
        String cartTitle = productPageService.getLastProductTitle();
        Assert.assertEquals(cartTitle, productTitle, "The product isn't added to cart");
    }

    @AfterClass
    public void AfterClass() {
        driver.quit();
    }
}
