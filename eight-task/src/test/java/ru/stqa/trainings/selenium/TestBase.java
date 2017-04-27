package ru.stqa.trainings.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Марина on 27.04.2017.
 */
public class TestBase {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,30);
    }

    public boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException ex){
            throw ex;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    public boolean areElementsPresent(By locator){
        return driver.findElements(locator).size()>0;
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
