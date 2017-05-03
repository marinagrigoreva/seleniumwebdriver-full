package ru.stqa.trainings.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Марина on 03.05.2017.
 */
public class ElevenTask extends TestBase {
    String url="http://localhost/litecart/en/";


    @Test //а) на главной странице и на странице товара совпадает текст названия товара
    public void createAccount(){
        driver.get(url);
        //Заполняем поля
        driver.findElement(By.xpath("//a[contains(.,'Create Account')]")).click();
        driver.findElement(By.name("tax_id")).sendKeys("0123456789");
        driver.findElement(By.name("company")).sendKeys("TestCompany");
        driver.findElement(By.name("firstname")).sendKeys("TestName");
        driver.findElement(By.name("lastname")).sendKeys("TestLastname");
        driver.findElement(By.name("address1")).sendKeys("123456, Testenburg, ul. Testovaya, dom 123A, kv 987");
        driver.findElement(By.name("address2")).sendKeys("Testovaya obl");
        driver.findElement(By.name("postcode")).sendKeys("44308");
        driver.findElement(By.name("city")).sendKeys("Testenburg");
        WebElement selectCountryElem = driver.findElement(By.name("country_code"));
        Select selectCountry = new Select(selectCountryElem);
        selectCountry.selectByVisibleText("United States");
        WebElement selectZoneElem = driver.findElement(By.name("zone_code"));
        Select selectZone = new Select(selectZoneElem);
        selectZone.selectByVisibleText("Hawaii");
        String date = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        driver.findElement(By.xpath("//form[@name='customer_form']//input[@name='email']")).sendKeys("grigoryeva"+date+"@test.ru");
        driver.findElement(By.name("phone")).sendKeys("+79271234567");
        driver.findElement(By.xpath("//input[@required='required'][@name='password']")).sendKeys("qwer1234");
        driver.findElement(By.name("confirmed_password")).sendKeys("qwer1234");
        driver.findElement(By.name("newsletter")).click();
        driver.findElement(By.name("create_account")).click(); //Создаем аккаунт
        //Проверяем что кнопки создать аккаунт нет, значит аккаунт создан успешно
        assertTrue(!isElementPresent(By.name("create_account")));

        driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click(); //Разлогиниваемся
        //Проверим что присутствует поле ввода почты, значит разлогинились
        assertTrue(isElementPresent(By.name("email")));

        //Вводим почту\пароль и логинимся
        driver.findElement(By.name("email")).sendKeys("marina-grigoryeva@test.ru");
        driver.findElement(By.name("password")).sendKeys("qwer1234");
        driver.findElement(By.name("login")).click();

        //Пристуствует кнопка выхода, значит залогинились
        assertTrue(isElementPresent(By.xpath("//a[contains(.,'Logout')]")));

        //Разлогиниваемся
        driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click();
        assertTrue(!isElementPresent(By.xpath("//a[contains(.,'Logout')]"))); //Проверяем что кнопка выйти отсутствует

    }




}
