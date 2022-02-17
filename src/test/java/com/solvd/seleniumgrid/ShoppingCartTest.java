package com.solvd.seleniumgrid;

import com.solvd.seleniumgrid.web.pages.ShoppingCartPage;
import com.solvd.seleniumgrid.web.service.ProductPageService;
import com.solvd.seleniumgrid.web.service.TestDataService;
import com.solvd.seleniumgrid.web.util.DriverUtil;
import com.solvd.seleniumgrid.web.util.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShoppingCartTest extends AbstractTest {

    private static ProductPageService productPageService;

    @BeforeClass
    public void beforeCartTest() {
        WebDriver driver = DriverUtil.getDriver("cart");
        productPageService = new ProductPageService(driver);
    }

    @Test
    public void addToBagTest() {
        WebDriver driver = DriverUtil.getDriver("cart");
        productPageService.openProductPage();
        String productTitle = productPageService.getProductTitleText();
        ShoppingCartPage shoppingCartPage =
                productPageService.addProductToBag(TestDataService.getValue("size"));
        WaitUtil.waitVisibility(driver, shoppingCartPage.getTitle());
        Assert.assertTrue(shoppingCartPage.getTitle().isDisplayed(), "Cart isn't opened");
        String cartTitle = productPageService.getLastProductTitle();
        Assert.assertEquals(cartTitle, productTitle, "The product isn't added to cart");
    }
}
