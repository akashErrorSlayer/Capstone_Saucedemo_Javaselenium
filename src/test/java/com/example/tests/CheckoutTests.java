package com.example.tests;

import com.example.framework.BaseTest;
import com.example.pages.CartPage;
import com.example.pages.CheckoutPage;
import com.example.pages.InventoryPage;
import com.example.pages.LoginPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

	@Test(priority = 1)
	public void checkout_shouldCompleteOrder() {
	    new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();

	    InventoryPage inv = new InventoryPage(driver);
	    inv.addItemToCart("Sauce Labs Backpack");
	    inv.openCart();

	    CartPage cart = new CartPage(driver);
	    cart.clickCheckout();

	    CheckoutPage co = new CheckoutPage(driver);
	    co.fillInformation("John", "Doe", "560001");
	    co.continueCheckout();
	    waitForVisibility(By.id("finish"), 10);
	    co.finishOrder();
        waitForVisibility(By.cssSelector(".complete-header"), 10);
	    String success = co.getSuccessHeader();
	    System.out.println("Success header: " + success);
	    Assert.assertTrue(success.toLowerCase().contains("thank"), 
	            "Expected success message to contain 'thank'. Actual: " + success);
	}

	@Test(priority = 2)
	public void checkout_missingFirstName_shouldShowError() {
	    new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();

	    InventoryPage inv = new InventoryPage(driver);
	    inv.addItemToCart("Sauce Labs Backpack");
	    inv.openCart();

	    CartPage cart = new CartPage(driver);
	    cart.clickCheckout();

	    CheckoutPage co = new CheckoutPage(driver);
	    co.fillInformation("", "Doe", "560001"); // missing first name
	    co.continueCheckout();
        waitForVisibility(By.cssSelector("h3[data-test='error']"), 10);
	    String error = co.getErrorText();
	    System.out.println("Error text: " + error);
	    Assert.assertTrue(error != null && error.toLowerCase().contains("first name is required"),
	            "Expected error for missing first name. Actual: " + error);
	    
	}

	@Test(priority = 3)
	public void checkout_missingLastName_shouldShowError() {
	    new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();

	    InventoryPage inv = new InventoryPage(driver);
	    inv.addItemToCart("Sauce Labs Backpack");
	    inv.openCart();

	    CartPage cart = new CartPage(driver);
	    cart.clickCheckout();

	    CheckoutPage co = new CheckoutPage(driver);
	    co.fillInformation("Jane", "", "560001"); // missing last name
	    co.continueCheckout();
	    waitForVisibility(By.cssSelector("h3[data-test='error']"), 10);
	    String error = co.getErrorText();
	    System.out.println("Error text: " + error);
	    Assert.assertTrue(error != null && error.toLowerCase().contains("last name is required"),
	            "Expected error for missing last name. Actual: " + error);
	}

	@Test(priority = 4)
	public void checkout_missingPostalCode_shouldShowError() {
	    new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();

	    InventoryPage inv = new InventoryPage(driver);
	    inv.addItemToCart("Sauce Labs Bolt T-Shirt");
	    inv.openCart();

	    CartPage cart = new CartPage(driver);
	    cart.clickCheckout();

	    CheckoutPage co = new CheckoutPage(driver);
	    co.fillInformation("Jane", "Doe", ""); // no postal code
	    co.continueCheckout();
	    waitForVisibility(By.cssSelector("h3[data-test='error']"), 10);
	    String error = co.getErrorText();
	    System.out.println("Error text: " + error);
	    Assert.assertTrue(error != null && error.toLowerCase().contains("postal code is required"),
	            "Expected error for missing postal code. Actual: " + error);
	}

}
