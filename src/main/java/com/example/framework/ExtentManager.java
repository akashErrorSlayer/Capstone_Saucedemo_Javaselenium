package com.example.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            try {
                Path outDir = Paths.get("test-output","reports");
                if (!Files.exists(outDir)) {
                    Files.createDirectories(outDir);
                }
                ExtentSparkReporter spark = new ExtentSparkReporter(outDir.resolve("ExtentReport.html").toString());
                extent = new ExtentReports();
                extent.attachReporter(spark);
                extent.setSystemInfo("Project", "SauceDemo");
                extent.setSystemInfo("Framework", "Selenium-TestNG");
            } catch (Exception e) {
                throw new RuntimeException("Failed to init ExtentReports", e);
            }
        }
        return extent;
    }
}
