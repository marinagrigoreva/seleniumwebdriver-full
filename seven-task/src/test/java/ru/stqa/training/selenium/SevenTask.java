package ru.stqa.training.selenium;
import org.junit.Test;
import org.openqa.selenium.*;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by Марина on 27.04.2017.
 */
public class SevenTask extends TestBase{



    @Test
    public void HeaderPresent(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.xpath("//button[@name='login']")).click();
        List<WebElement> menu= driver.findElements(By.xpath("//li[@id='app-']/a/span[@class='name']")); //Найдем все пункты меню
        int length = menu.size();  //Узнаем количество пунктов меню
        int i=1;
        while (i<=length){
            driver.findElement(By.xpath("//li[@id='app-']["+i+"]/a/span[@class='name']")).click(); //Кликнуть по пункту меню номер i
            assertTrue(isElementPresent(By.xpath("//h1"))); //Проверить что есть заголовок h1
            List<WebElement> submenu= driver.findElements(By.xpath("//li[@id='app-']/ul/li[not(contains(@class, 'selected'))]/a/span[@class='name']")); // Найдем подпункты меню
            int sublength = submenu.size();  //Узнаем количество подпунктов меню
            if (sublength>0){  //Если количество подпунктов больше 0, то
                int j=1;
                while (j<=sublength){
                    //кликнуть по подпункту меню с номером j
                    driver.findElement(By.xpath("//li[@id='app-']/ul/li[not(contains(@class, 'selected'))]["+j+"]/a/span[@class='name']")).click();
                    assertTrue(isElementPresent(By.xpath("//h1"))); //Проверить что есть заголовок h1
                    j++;
                }
            }
            i++;
        }
    }


}
