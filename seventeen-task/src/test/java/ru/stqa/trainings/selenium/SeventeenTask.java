package ru.stqa.trainings.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * Created by Марина on 17.05.2017.
 */
public class SeventeenTask extends TestBase {


    @Test
    public void checkLogs(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin"); //логин
        driver.findElement(By.name("password")).sendKeys("admin"); //пароль
        driver.findElement(By.xpath("//button[@name='login']")).click(); //вход

        driver.findElement(By.xpath("//span[contains(.,'Catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(.,'Rubber Ducks')]")).click();
        driver.findElement(By.xpath("//a[contains(.,'Subcategory')]")).click();


        List<WebElement> products = driver.findElements(By.xpath("//tbody/tr/td[3]/a[contains(.,'Duck')]"));
        int length = products.size(); System.out.println(length);
        int i=0;
        while (i<length){ System.out.println(i);
            List<WebElement> products1 = driver.findElements(By.xpath("//tbody/tr/td[3]/a[contains(.,'Duck')]"));
            products1.get(i).click();
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            assertTrue(logs.size()==0);
            driver.navigate().back();
            driver.findElement(By.xpath("//a[contains(.,'Rubber Ducks')]")).click();
            driver.findElement(By.xpath("//a[contains(.,'Subcategory')]")).click();
            i++;
        }

    }


}
