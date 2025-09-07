package com.example.stepDefinitions;

import com.example.framework.DriverFactory;
import com.example.pages.InventoryPage;
import com.example.pages.LoginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventorySteps {

    private WebDriver driver;
    private InventoryPage inventoryPage;

    @Given("I am logged in with username {string} and password {string}")
    public void i_am_logged_in_with_username_and_password(String username, String password) {
        // Initialize driver
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");

        // Perform login
        new LoginPage(driver).enterUsername(username).enterPassword(password).clickLogin();

        // Wait for inventory page to load
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));

        inventoryPage = new InventoryPage(driver);
    }

    @Then("the inventory page title should be {string}")
    public void the_inventory_page_title_should_be(String expectedTitle) {
        Assert.assertEquals(inventoryPage.getTitle(), expectedTitle, "Page title should match.");
        DriverFactory.quitDriver();
    }

    @When("I add the first item to the cart")
    public void i_add_the_first_item_to_the_cart() {
        inventoryPage.addFirstItemToCart();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_badge")));
    }

    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String expectedCount) {
        int actualCount = inventoryPage.getCartBadgeCount();
        Assert.assertEquals(String.valueOf(actualCount), expectedCount, "Cart badge count mismatch!");
        DriverFactory.quitDriver();
    }
}
