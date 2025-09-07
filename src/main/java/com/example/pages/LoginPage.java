package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage enterUsername(String user) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        return this;
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public String getErrorText() {
        try {
            return driver.findElement(error).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
