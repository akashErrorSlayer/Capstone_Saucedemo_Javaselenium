package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {
    private final WebDriver driver;

    private final By pageTitle = By.cssSelector(".title");
    private final By firstAddToCart = By.cssSelector(".inventory_item:first-of-type button.btn_inventory");
    private final By shoppingCart = By.id("shopping_cart_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void addFirstItemToCart() {
        driver.findElement(firstAddToCart).click();
    }

    public void openCart() {
        driver.findElement(shoppingCart).click();
    }
    public void addItemToCartByName(String itemName) {
        driver.findElement(By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button")).click();
    }

	public void addItemToCart(String itemName) {
		
	}
	 public int getCartBadgeCount() {
	        try {
	            String count = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
	            return Integer.parseInt(count);
	        } catch (Exception e) {
	            return 0; // no badge means cart is empty
	        }
	 }
}
