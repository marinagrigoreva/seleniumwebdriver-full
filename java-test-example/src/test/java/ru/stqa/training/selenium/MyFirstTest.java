package ru.stqa.training.selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;




/**
 * Created by Марина on 18.04.2017.
 */
public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,30);
    }

    @Test
    public void MyFirstTest(){
        driver.get("https://www.google.ru");
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
