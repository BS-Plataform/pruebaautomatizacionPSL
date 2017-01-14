package com.psl.pruebas.selenium;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by marcos on 13/01/17.
 */
public class PslHospitalesChromeTest {

    private WebDriver driver;
    private String baseUrl;
    private ScreenshotHelper screenshotHelper;

    @Before
    public void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe");
        baseUrl = "http://automatizacion.herokuapp.com/marcos/home";
        driver = new ChromeDriver();
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
    public void validatePageAddDcotor() throws IOException {
        driver.findElement(By.linkText("Agregar Doctor")).click();
        assertEquals( "Agregar Doctor", driver.getTitle());

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
    public void validateAddPaciente() throws IOException {
        driver.findElement(By.linkText("Agregar Paciente")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("Lauriano");
        driver.findElement(By.name("last_name")).clear();
        driver.findElement(By.name("last_name")).sendKeys("Marcos");
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("09855");
        driver.findElement(By.name("identification")).clear();
        driver.findElement(By.name("identification")).sendKeys("097777");
        driver.findElement(By.name("prepaid")).click();
        driver.findElement(By.linkText("Guardar")).click();

        assertEquals( "Lauriano",driver.findElement(By.className("patientName")).getText() );

    }

    @Test
    public void validateAddHospitalAndConsult() throws IOException {
        driver.findElement(By.linkText("Agregar Hospital")).click();
        driver.findElement(By.cssSelector("input.form-control")).clear();
        driver.findElement(By.cssSelector("input.form-control")).sendKeys("12345");
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("Hospital central");
        driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
        driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("11111");
        driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
        driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("3333");
        new Select(driver.findElement(By.id("department"))).selectByVisibleText("Antioquia");
        new Select(driver.findElement(By.id("municipality"))).selectByVisibleText("Abejorral");
        driver.findElement(By.linkText("Guardar")).click();

        assertEquals( "Código",driver.findElement(By.cssSelector(".table-striped > tbody > tr:nth-child(2n+1) > td, .table-striped > tbody > tr:nth-child(2n+1) > th")).getText() );


       driver.findElement(By.linkText("Inicio")).click();
        driver.findElement(By.linkText("Ver Hospitales")).click();

        assertEquals( "12345",driver.findElement(By.cssSelector(".table-striped > tbody > tr:nth-child(2n+1) > td, .table-striped > tbody > tr:nth-child(2n+1) >th")).getText() );

    }

    private class ScreenshotHelper {

        public void saveScreenshot(String screenshotFileName) throws IOException {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotFileName));
        }
    }
}