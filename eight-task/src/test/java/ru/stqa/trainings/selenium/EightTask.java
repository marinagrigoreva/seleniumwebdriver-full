package ru.stqa.trainings.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Марина on 27.04.2017.
 */
public class EightTask extends TestBase {

    @Test
    public void CheckSticker(){
        driver.get("http://localhost/litecart/en/");
        String[] box = {"box-campaigns", "box-most-popular", "box-latest-products"};
        String[] menu = {"Campaign Products", "Popular Products", "Latest Products"}; //Текст вкладок меню товаров
        int t=0;
        while (t<3){
            driver.findElement(By.xpath("//a[contains(.,'"+menu[t]+"')]")).click(); //Кликаем на вкладку меню товаров
            List<WebElement> products= driver.findElements(By.xpath("//div[@id='"+box[t]+"']/div/div")); //Получим список товаров
            int length = products.size();
            //Узнаем количество товаров на странице
            int i=0;
            while (i<length){ //Перебираем товары
                //Получим список стикеров имеющихся в товаре №i
                List<WebElement> stickers = products.get(i).findElements(By.xpath(".//div[contains(@class,'sticker')]"));
                int count = stickers.size();  //Узнаем количество стикеров имеющихся в товаре №i
                assertTrue(count==1); //Проверим что товар имеет только один стикер
                i++;
            }
            t++;
        }
    }
}
