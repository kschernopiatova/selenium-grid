package com.solvd.seleniumgrid.web.service;

import com.solvd.seleniumgrid.web.pages.AccountPage;
import com.solvd.seleniumgrid.web.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginService {

    private final LoginPage loginPage;
    private final AccountPage accountPage;

    public LoginService(WebDriver driver) {
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
    }

    public AccountPage login(String email, String pass) {
        loginPage.open();
        loginPage.insert(loginPage.getEmailField(), email);
        loginPage.insert(loginPage.getPasswordField(), pass);
        loginPage.click(loginPage.getLoginButton());
        return accountPage;
    }
}
