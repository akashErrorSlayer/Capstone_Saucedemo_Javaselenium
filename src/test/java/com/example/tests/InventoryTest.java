//package com.example.tests;
//
//import com.example.framework.BaseTest;
//import com.example.pages.InventoryPage;
//import com.example.pages.LoginPage;
//
//import org.openqa.selenium.By;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class InventoryTest extends BaseTest {
//
//    @Test(priority = 1)
//    public void verifyInventoryPageTitle() {
//        new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();
//        waitForVisibility(By.cssSelector(".title"), 20);
//        InventoryPage inventoryPage = new InventoryPage(driver);
//        Assert.assertEquals(inventoryPage.getTitle(), "Products", "Page title should be Products.");
//    }
//
//    @Test(priority = 2)
//    public void addFirstItemToCart_shouldIncreaseCartBadge() {
//        new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();
//        waitForVisibility(By.cssSelector(".title"), 10);
//        InventoryPage inv = new InventoryPage(driver);
//        inv.addFirstItemToCart();
//        waitForVisibility(By.cssSelector(".shopping_cart_badge"), 10);
//        Assert.assertEquals(inv.getCartBadgeCount(), 1, "Cart badge should show 1 after adding first item.");
//    }
//
// 
//    @Test(priority = 3)
//    public void cartBadgeShouldNotAppearWhenNoItemsAdded() {
//        new LoginPage(driver).enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();
//        waitForVisibility(By.cssSelector(".title"), 10);
//        InventoryPage inv = new InventoryPage(driver);
//        Assert.assertEquals(inv.getCartBadgeCount(), 0, "Cart badge should not appear if no items are added.");
//    }
//}
