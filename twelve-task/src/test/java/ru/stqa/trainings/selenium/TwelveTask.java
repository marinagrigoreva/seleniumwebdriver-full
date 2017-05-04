package ru.stqa.trainings.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Марина on 03.05.2017.
 */
public class TwelveTask extends TestBase {

    @Test

    public void AddNewProduct(){
        String path=System.getProperty("user.dir"); System.out.println(path);
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin"); //логин
        driver.findElement(By.name("password")).sendKeys("admin"); //пароль
        driver.findElement(By.xpath("//button[@name='login']")).click(); //вход

        driver.findElement(By.xpath("//span[contains(.,'Catalog')]")).click();
        driver.findElement(By.xpath("//ul[@class='list-inline pull-right']/li[3]")).click();

        driver.findElement(By.xpath("//label[@class='btn btn-default']")).click();
        driver.findElement(By.name("code")).sendKeys("1234567890");
        driver.findElement(By.name("name[en]")).sendKeys("Cat");
        driver.findElement(By.name("sku")).sendKeys("123123123");
        driver.findElement(By.name("gtin")).sendKeys("456456456");
        driver.findElement(By.name("taric")).sendKeys("789789789");
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("100");
        driver.findElement(By.xpath("//input[@value='1'][@name='categories[]']")).click();
        driver.findElement(By.xpath("//input[@value='2'][@name='categories[]']")).click();
        WebElement selectCategoryElem = driver.findElement(By.name("default_category_id"));
        Select selectCategory = new Select(selectCategoryElem);
        selectCategory.selectByVisibleText("Subcategory");
        driver.findElement(By.xpath("//input[@value='1-1'][@name='product_groups[]']")).click();
        driver.findElement(By.name("weight")).clear();
        driver.findElement(By.name("weight")).sendKeys("5");
        WebElement selectWeightElem = driver.findElement(By.name("weight_class"));
        Select selectWeight = new Select(selectWeightElem);
        selectWeight.selectByVisibleText("lb");

        driver.findElement(By.name("dim_x")).sendKeys("20");
        driver.findElement(By.name("dim_y")).sendKeys("25");
        driver.findElement(By.name("dim_z")).sendKeys("45");
        WebElement selectTypeElem = driver.findElement(By.name("dim_class"));
        Select selectType = new Select(selectTypeElem);
        selectType.selectByVisibleText("cm");

        WebElement selectDeliveryElem = driver.findElement(By.name("delivery_status_id"));
        Select selectDelivery = new Select(selectDeliveryElem);
        selectDelivery.selectByVisibleText("3-5 days");
        WebElement selectSoldElem = driver.findElement(By.name("sold_out_status_id"));
        Select selectSold = new Select(selectSoldElem);
        selectSold.selectByVisibleText("-- Select --");

        driver.findElement(By.name("date_valid_from")).sendKeys(Keys.HOME + "01.01.2016");
        driver.findElement(By.name("date_valid_to")).sendKeys(Keys.HOME + "01.01.2018");
        driver.findElement(By.name("new_images[]")).sendKeys(path+"\\src\\test\\resources\\123.jpg");

        //Переход на вкладку Information
        driver.findElement(By.xpath("//a[contains(.,'Information')]")).click();
        wait.until(presenceOfElementLocated(By.name("manufacturer_id")));

        WebElement selectManufacturerElem = driver.findElement(By.name("manufacturer_id"));
        Select selectManufacturer = new Select(selectManufacturerElem);
        selectManufacturer.selectByVisibleText("ACME Corp.");
        WebElement selectSupplierElem = driver.findElement(By.name("supplier_id"));
        Select selectSupplier = new Select(selectSupplierElem);
        selectSupplier.selectByVisibleText("-- Select --");
        driver.findElement(By.name("keywords")).sendKeys("cat white gray");
        driver.findElement(By.name("short_description[en]")).sendKeys("cat");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("This is a very beautiful cat."+Keys.ENTER+"The cat accustomed to the toilet.");
        driver.findElement(By.name("attributes[en]")).sendKeys("attributes");
        driver.findElement(By.name("head_title[en]")).sendKeys("Head Title");
        driver.findElement(By.name("meta_description[en]")).sendKeys("Meta Description");

        //Переход на вкладку Prices
        driver.findElement(By.xpath("//a[contains(.,'Prices')]")).click();
        wait.until(presenceOfElementLocated(By.name("purchase_price")));

        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("50");
        WebElement selectPurchaseElem = driver.findElement(By.name("purchase_price_currency_code"));
        Select selectPurchase = new Select(selectPurchaseElem);
        selectPurchase.selectByVisibleText("US Dollars");
        WebElement selectTaxElem = driver.findElement(By.name("tax_class_id"));
        Select selectTax = new Select(selectTaxElem);
        selectTax.selectByVisibleText("-- Select --");
//        driver.findElement(By.name("prices[USD]")).clear();
        driver.findElement(By.name("prices[USD]")).sendKeys("50");
        driver.findElement(By.name("prices[EUR]")).sendKeys("43");
//        driver.findElement(By.name("gross_prices[USD]")).clear();
//        driver.findElement(By.name("gross_prices[USD]")).sendKeys("51");
 //       driver.findElement(By.name("gross_prices[EUR]")).sendKeys("44");

        driver.findElement(By.name("save")).click();
        assertTrue(isElementPresent(By.xpath("//a[contains(.,'Cat')]")));
    }
}
