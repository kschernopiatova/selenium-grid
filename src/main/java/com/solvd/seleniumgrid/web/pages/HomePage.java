package com.solvd.seleniumgrid.web.pages;

import com.solvd.seleniumgrid.web.service.ConfigData;
import com.solvd.seleniumgrid.web.service.ConfigService;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver) {
        super(driver);
        String pageUrl = ConfigService.getValue(ConfigData.BASE_URL);
        setUrl(pageUrl);
    }
}
