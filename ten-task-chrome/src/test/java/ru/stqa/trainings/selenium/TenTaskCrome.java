package ru.stqa.trainings.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Марина on 29.04.2017.
 */
public class TenTaskCrome extends TestBase {
    String url="http://localhost/litecart/en/";


    @Test //а) на главной странице и на странице товара совпадает текст названия товара
    public void comparisonName(){
        driver.get(url);
        //Получить название товара на главной странице
        String nameHP = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']//div[@class='name']")).getText();

        driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click(); //Открыть товар
        wait.until(presenceOfElementLocated(By.name("add_cart_product")));

        //Получить название товара на странице товара
        String nameTP = driver.findElement(By.xpath("//h1")).getText();
        assertTrue(nameHP.equals(nameTP)); //Сравнить название товара на главной и странице товара
    }

    @Test //б) на главной странице и на странице товара совпадают цены (обычная и акционная)
    public void comparisonPrice(){
        driver.get(url);
        //Получить обычую цену товара на главной странице
        String regPriceHP = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']//s[@class='regular-price']")).getText();
        //Получить акционную цену товара на главной странице
        String actPriceHP = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']//strong[@class='campaign-price']")).getText();

        driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click(); //Открыть товар
        wait.until(presenceOfElementLocated(By.name("add_cart_product")));

        //Получить обычную цену товара на странице товара
        String regPriceTP = driver.findElement(By.xpath("//del")).getText();
        assertTrue(regPriceHP.equals(regPriceTP)); //Убедиться что обычная цена товара одинакова на главной и странице товара
        //Получить акционную цену товара на странице товара
        String actPriceTP = driver.findElement(By.xpath("//div[@id='box-product']//strong")).getText();
        assertTrue(actPriceHP.equals(actPriceTP)); //Убедиться что акционная цена товара одинакова на главной и странице товара

    }

    @Test //в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
    //(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
    public void comparisonRegularPrice(){
        driver.get(url);
        //Получить свойство перечеркутого текста обычной цены на главной странице
        String regTextLineHP = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']//s[@class='regular-price']")).getCssValue("text-decoration-line");
        //Получить цвет обычной цены на главной странице
        String regColorHP = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']//s[@class='regular-price']")).getCssValue("color");
        //Проверим что на главной странице обычная цена перечеркнута и R=G, а G=B
        assertTrue(regTextLineHP.equals("line-through")& getRgb(regColorHP)[0].equals(getRgb(regColorHP)[1])& getRgb(regColorHP)[1].equals(getRgb(regColorHP)[2]));

        driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click(); //Открыть товар
        wait.until(presenceOfElementLocated(By.name("add_cart_product")));

        //Получить цвет обычной цены на странице товара
        String regColorTP = driver.findElement(By.xpath("//del")).getCssValue("color");
        //Убедиться что на странице товара цвет обычной цены серый, т.е r=g=b
        assertTrue(getRgb(regColorTP)[0].equals(getRgb(regColorTP)[1])&getRgb(regColorTP)[1].equals(getRgb(regColorTP)[2]));
    }

    @Test //г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
     //(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
    public void comparisonActionPrice(){
        driver.get(url);

        //Получим стиль текста акционной цены на главной странице
        String actTextStyleHP = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']//strong[@class='campaign-price']")).getCssValue("font-weight");
        System.out.println(actTextStyleHP);
        //Получим цвет текста акционной цены на главной странице
        String actColorHP = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']//strong[@class='campaign-price']")).getCssValue("color");
        System.out.println(actColorHP);
        //Проверим что на гравной странице акционная цена жирная и B=G, а G=0
        assertTrue(actTextStyleHP.equals("bold")& getRgb(actColorHP)[2].equals(getRgb(actColorHP)[1])& getRgb(actColorHP)[1].equals("0"));

        driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click(); //Открыть товар
        wait.until(presenceOfElementLocated(By.name("add_cart_product")));

        //Получить цвет акционной цены на странице товара
        String actColorTP = driver.findElement(By.xpath("//div[@id='box-product']//strong")).getCssValue("color");
        //Убедиться что на странице товара цвет акционной цены красный, т.е g=b=0
        assertTrue(getRgb(actColorTP)[1].equals(getRgb(actColorTP)[2])&getRgb(actColorTP)[2].equals("0"));
    }

    @Test //г) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
    public void comparisonSizeText(){
        driver.get(url);
        //Получим размер текста обычной цены на главной странице
        String rsizeHP = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']//s[@class='regular-price']")).getCssValue("font-size");
        rsizeHP = rsizeHP.replace("px","");
        float HP= Float.parseFloat(rsizeHP);
        //Получим размер текста акционной цены на главной странице
        String asizeHP = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']//strong[@class='campaign-price']")).getCssValue("font-size");
        asizeHP = asizeHP.replace("px","");
        float sizeAHP= Float.parseFloat(asizeHP);
        //Проверим что текст акционной цены крупнее чем обычной на главной странице
        assertTrue(sizeAHP>HP);

        driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click(); //Открыть товар
        wait.until(presenceOfElementLocated(By.name("add_cart_product")));

        //Размер текста обычной цены на странице товара
        String rsizeTP = driver.findElement(By.xpath("//del")).getCssValue("font-size");
        rsizeTP = rsizeTP.replace("px","");
        float sizeRTP= Float.parseFloat(rsizeHP);
        //Размер текста акционной цены на странице товара
        String asizeTP = driver.findElement(By.xpath("//div[@id='box-product']//strong")).getCssValue("font-size");
        asizeTP = asizeTP.replace("px","");
        float sizeATP= Float.parseFloat(asizeTP);
        //Проверим что текст акционной цены крупнее чем обычной на странице товара
        assertTrue(sizeATP>sizeRTP);

    }

}
