package ru.stqa.trainings.selenium;

import com.sun.xml.internal.ws.org.objectweb.asm.ByteVector;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 * Created by Марина on 10.05.2017.
 */
public class ThirteenTask extends TestBase{

    String url="http://localhost/litecart/en/";


    @Test
    public void addToCart(){
        driver.get(url);
        int i =0;
        while (i<3){
            String quantity = driver.findElement(By.cssSelector("span.quantity")).getText(); //Получить количество товаров в корзине
            int count = Integer.parseInt(quantity); //Получить из строки количество товаров
            count++; //Увеличить количество на один
            i++;
            driver.findElement(By.xpath("//div[@id='box-campaigns']/div/div")).click(); //Выбрать первый товар на странице
            wait.until(presenceOfElementLocated(By.name("options[Size]"))); //Дождаться появления элемента списка размеров товара
            WebElement selectSizeElem = driver.findElement(By.name("options[Size]"));
            Select selectSize = new Select(selectSizeElem);
            selectSize.selectByIndex(i); //Выбрать в списке размеров пункт по номеру i
            driver.findElement(By.name("add_cart_product")).click(); //Добавить товар в корзину
            //Дождаться когда в корзине станет на один товар больше, чем было
            wait.until(presenceOfElementLocated(By.xpath("//span[contains(.,'"+count+"')][@class='quantity']")));
            driver.findElement(By.xpath("//a[contains(.,'Home')]")).click(); //Вернуться на главную страницу
        }
        driver.findElement(By.cssSelector("div#cart")).click(); //Перейти в корзину
        wait.until(numberOfElementsToBe(By.xpath("//button[@name='remove_cart_item']"), 3)); //Дождаться появления трех кнопоку удаления товара
        int j = 0;
        while (j<3){
            //Получить элемент суммы Subtotal
            WebElement sum = driver.findElement(By.xpath("//div[@id='order_confirmation-wrapper']//tbody//tr[1]/td[2]"));
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click(); //Удалить товар
            wait.until(stalenessOf(sum)); //Дождаться когда элемент суммы изменится
            j++;
        }
    }

}
