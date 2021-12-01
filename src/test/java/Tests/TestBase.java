package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestBase {
    static private Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected WebDriver driver;


    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("Webdriver initialized");
    }

    @BeforeEach
    void setUp() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", Helpers.DOWNLOAD_DIR);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        logger.info("Webdriver window start");
        driver.manage().window().maximize();
        logger.info("Webdriver window maximized");

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        logger.info("Webdriver window closed");
    }

}