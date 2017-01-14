package com.psl.pruebas.selenium;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by marcos on 13/01/17.
 */
public class PslHospitalesFirefoxTest {
    private String baseUrl;
    private WebDriver driver;
    private ScreenshotHelper screenshotHelper;

    @Before
    public void openBrowser() {

        System.setProperty("webdriver.gecko.driver", "D:\\Downloads\\geckodriver-v0.13.0-win64\\geckodriver.exe");
        baseUrl = "http://automatizacion.herokuapp.com/marcos/home";
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        screenshotHelper = new ScreenshotHelper();
    }

    @After
    public void saveScreenshotAndCloseBrowser() throws IOException {
        screenshotHelper.saveScreenshot("screenshot.png");
        driver.quit();
    }

    @Test
    public void validatePageTitle() throws IOException {
        assertEquals("El titulo es correcto", "PSL - Ejercicio de automatización", driver.getTitle());

    }

    @Test
    public void validateAddDcotor() throws IOException {
        driver.findElement(By.linkText("Agregar Doctor")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("miguel");
        driver.findElement(By.id("last_name")).clear();
        driver.findElement(By.id("last_name")).sendKeys("arcos");
        driver.findElement(By.id("telephone")).clear();
        driver.findElement(By.id("telephone")).sendKeys("123456789");
        driver.findElement(By.id("identification")).clear();
        driver.findElement(By.id("identification")).sendKeys("123456789");
        driver.findElement(By.linkText("Guardar")).click();

        assertEquals( "miguel",driver.findElement(By.id("firstName")).getText() );

    }

    @Test
    public void validatePageAddDcotor() throws IOException {
        driver.findElement(By.linkText("Agregar Doctor")).click();
        assertEquals("El titulo es correcto", "Agregar Doctor", driver.getTitle());

    }

    private class ScreenshotHelper {

        public void saveScreenshot(String screenshotFileName) throws IOException {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotFileName));
        }
    }
}