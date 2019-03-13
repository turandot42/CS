package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            Map<String, Object> deviceMetrics = new HashMap<String, Object>();

            deviceMetrics.put("width", 1491);

            deviceMetrics.put("height", 3640);

            deviceMetrics.put("pixelRatio", 1.0);


            Map<String, Object> mobileEmulation = new HashMap<String, Object>();

            mobileEmulation.put("deviceMetrics", deviceMetrics);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
