package com.example.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.example.framework.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentTestManager.setTest(test);
        test.log(Status.INFO, "Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            String path = ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName() + "_PASS");
            ExtentTestManager.getTest().pass("Test passed",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } else {
            ExtentTestManager.getTest().pass("Test passed (no screenshot, driver was null)");
        }
        ExtentTestManager.unload();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            String path = ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName() + "_FAIL");
            ExtentTestManager.getTest().fail(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } else {
            ExtentTestManager.getTest().fail(result.getThrowable())
                    .log(Status.WARNING, "Driver was null, screenshot not captured.");
        }
        ExtentTestManager.unload();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test skipped: " + result.getSkipCausedBy().toString());
        ExtentTestManager.unload();
    }

    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
