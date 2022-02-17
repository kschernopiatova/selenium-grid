package com.solvd.seleniumgrid.web.pages;

import com.solvd.seleniumgrid.web.service.ConfigData;
import com.solvd.seleniumgrid.web.service.ConfigService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        String pageUrl = ConfigService.getValue(ConfigData.BASE_URL) + "/account-login";
        setUrl(pageUrl);
    }

    public LoginPage(WebDriver driver, String path) {
        super(driver);
        String pageUrl = ConfigService.getValue(ConfigData.BASE_URL);
        setUrl(pageUrl + path);
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }
}
