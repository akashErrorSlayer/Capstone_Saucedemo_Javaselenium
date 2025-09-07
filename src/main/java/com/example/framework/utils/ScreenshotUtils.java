package com.example.framework.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String namePrefix) {
        try {
            Path outDir = Paths.get("test-output", "screenshots");
            if (!Files.exists(outDir)) {
                Files.createDirectories(outDir);
            }
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = String.format("%s_%s_%s.png", namePrefix, timestamp, UUID.randomUUID().toString().substring(0,8));
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = outDir.resolve(filename).toFile();
            FileUtils.copyFile(src, dest);
            return dest.getPath();
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }
}
