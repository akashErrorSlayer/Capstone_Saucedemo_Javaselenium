package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    private final By checkoutBtn = By.id("checkout");
    private final By continueShoppingBtn = By.id("continue-shopping");


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartCount() {
        return driver.findElements(By.cssSelector(".cart_item")).size();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }
    public void removeFirstItem() {
        driver.findElement(By.cssSelector(".cart_item button")).click();
    }

    public boolean isItemInCart(String itemName) {
        return driver.findElements(By.xpath("//div[@class='inventory_item_name' and text()='" + itemName + "']")).size() > 0;
    }
    
    public void clickContinueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }
}
