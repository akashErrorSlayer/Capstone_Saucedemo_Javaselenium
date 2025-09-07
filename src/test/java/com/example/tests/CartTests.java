package com.example.tests;

import com.example.framework.BaseTest;
import com.example.pages.CartPage;
import com.example.pages.InventoryPage;
import com.example.pages.LoginPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test
    public void addItemToCart_shouldIncreaseCartCount() {
        new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();
        waitForVisibility(By.cssSelector(".title"), 10);
        InventoryPage inv = new InventoryPage(driver);
        inv.addFirstItemToCart();
        inv.openCart();
        waitForVisibility(By.id("checkout"), 10);
        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.getCartCount(), 1, "Cart should have 1 item.");
    }
    @Test
    public void removeItemFromCart_shouldDecreaseCartCount() {
        new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();
        waitForVisibility(By.cssSelector(".title"), 10);
        InventoryPage inv = new InventoryPage(driver);
        inv.addFirstItemToCart();
        inv.openCart();
        waitForVisibility(By.cssSelector(".cart_list"), 10);
        CartPage cart = new CartPage(driver);
        cart.removeFirstItem();
        Assert.assertEquals(cart.getCartCount(), 0, "Cart should be empty after removing item.");
    }

    @Test
    public void addMultipleItems_shouldAppearInCart() {
        new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();
        waitForVisibility(By.cssSelector(".title"), 10);
        InventoryPage inv = new InventoryPage(driver);
        inv.addItemToCartByName("Sauce Labs Backpack");
        inv.addItemToCartByName("Sauce Labs Bike Light");
        inv.openCart();
        waitForVisibility(By.cssSelector(".cart_list"), 10);
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isItemInCart("Sauce Labs Backpack"), "Backpack should be in the cart.");
        Assert.assertTrue(cart.isItemInCart("Sauce Labs Bike Light"), "Bike Light should be in the cart.");
    }

    @Test
    public void cartShouldPersistAfterNavigation() {
        new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();
        waitForVisibility(By.cssSelector(".title"), 10);
        InventoryPage inv = new InventoryPage(driver);
        inv.addItemToCartByName("Sauce Labs Backpack");
        inv.openCart();
        waitForVisibility(By.cssSelector(".cart_list"), 10);
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isItemInCart("Sauce Labs Backpack"), "Backpack should be in the cart.");
        cart.clickContinueShopping();
        waitForVisibility(By.cssSelector(".inventory_list"), 10);
        inv.openCart();
        Assert.assertTrue(cart.isItemInCart("Sauce Labs Backpack"), "Item should still be in the cart after navigation.");
    }
    
    
}
