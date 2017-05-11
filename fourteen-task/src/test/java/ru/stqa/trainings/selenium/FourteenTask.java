package ru.stqa.trainings.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by Марина on 11.05.2017.
 */
public class FourteenTask  extends TestBase{

    @Test
    public void checkWindow(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin"); //логин
        driver.findElement(By.name("password")).sendKeys("admin"); //пароль
        driver.findElement(By.xpath("//button[@name='login']")).click(); //вход

        driver.findElement(By.xpath("//span[contains(.,'Countries')]")).click();
        driver.findElement(By.xpath("//a[@class='btn btn-default']")).click();
        String mainWindow = driver.getWindowHandle();
        List<WebElement> links = driver.findElements(By.xpath("//label/a[@target='_blank']"));
        int i=0;
        while (i<7){
            Set<String> oldWindows = driver.getWindowHandles();
            String LINK = links.get(i).getAttribute("href");
            int lastRr =LINK.lastIndexOf("/");
            String suburl=LINK.substring(lastRr, LINK.length());

            links.get(i).click();
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            wait.until(urlContains(suburl));
            driver.close();
            driver.switchTo().window(mainWindow);
            i++;

        }



    }



}
