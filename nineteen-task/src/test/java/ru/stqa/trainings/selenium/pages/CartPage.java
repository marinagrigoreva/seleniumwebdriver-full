package ru.stqa.trainings.selenium.pages;

/**
 * Created by Марина on 22.05.2017.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div[@id='order_confirmation-wrapper']//tbody//tr[1]/td[2]")
    public WebElement sum;

    @FindBy(xpath="//button[@name='remove_cart_item']")
    public WebElement buttonDelete;


    public WebElement sumCart(){return driver.findElement(By.xpath("//div[@id='order_confirmation-wrapper']//tbody//tr[1]/td[2]"));}

//    public WebElement buttonDelete(){return driver.findElement(By.xpath("//button[@name='remove_cart_item']"));}

    public void waitDeleteButtons(int countProducts){wait.until(numberOfElementsToBe(By.xpath("//button[@name='remove_cart_item']"), countProducts));}

}
