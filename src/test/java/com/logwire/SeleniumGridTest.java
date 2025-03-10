package com.logwire;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumGridTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        // Options pour Chrome
        ChromeOptions options = new ChromeOptions();
        options.setCapability("se:recordVideo", true);
        options.setCapability("se:screenResolution", "1920x1080");
        options.setCapability("se:name", "test_visit_basic_auth_secured_page (ChromeTests)");

        // Connexion au Selenium Grid
        driver = new RemoteWebDriver(new URL("http://192.168.1.96:4444/wd/hub"), options);
    }

    @Test
    public void testSeleniumWebsite() {
        // Accéder au site Selenium
        driver.get("https://www.selenium.dev");

        // Vérifier que le titre est correct
        assertEquals("Selenium", driver.getTitle(), "Le titre de la page doit être 'Selenium'");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
