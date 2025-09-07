package com.example.tests;

import com.example.framework.BaseTest;
import com.example.pages.InventoryPage;
import com.example.pages.LoginPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

	@Test(priority=1)
	public void testValidLoginNavigatesToInventoryPage() throws InterruptedException{
	    LoginPage loginPage = new LoginPage(driver);

	    loginPage.enterUsername("standard_user");
	    loginPage.enterPassword("secret_sauce");
	    loginPage.clickLogin();
	    waitForVisibility(By.cssSelector(".title"), 10);
	    InventoryPage inventoryPage = new InventoryPage(driver);
	    String actualTitle = inventoryPage.getTitle();
	    Assert.assertEquals(actualTitle, "Products", "Login failed – user not on Products page.");
	}


    @Test(priority=2)
    public void invalidLogin_shouldShowError() throws InterruptedException{
        LoginPage login = new LoginPage(driver);
        login.enterUsername("locked_out_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        waitForVisibility(By.cssSelector("[data-test='error']"), 10);
        String error = login.getErrorText();
        boolean verifyMsg=error != null && error.toLowerCase().contains("sorry");
        Assert.assertTrue(verifyMsg, "Expected an error message for locked out user.");
    }
    
    @Test(priority = 3)
    public void invalidPassword_shouldShowError() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("abcxyz");
        login.clickLogin();
        waitForVisibility(By.cssSelector("[data-test='error']"), 10);
        String error=login.getErrorText();
        boolean verifyMsg= error != null && error.contains("password do not match ");
        Assert.assertTrue(verifyMsg,"Incorrect Credentials");
    }

    @Test(priority = 4)
    public void blankCredentials_shouldShowError() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("");
        login.enterPassword("");
        login.clickLogin();
        waitForVisibility(By.cssSelector("[data-test='error']"), 10);
        String error=login.getErrorText();        
        Assert.assertTrue(error.contains("Username is required"),"Expected error for blank credentials");
    }
    @Test(priority=5)
    
    public void blankPassword() {
    	LoginPage login= new LoginPage(driver);
    	login.enterUsername("problem_user");
    	login.enterPassword("");
    	login.clickLogin();
    	waitForVisibility(By.cssSelector("[data-test='error']"), 10);
    	String error=login.getErrorText();
    	Assert.assertTrue(error.contains(" Password is required"),"error for blank Password");
    }
    
}
