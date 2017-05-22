package ru.stqa.trainings.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(name="add_cart_product")
     public WebElement addButton;

    @FindBy(xpath="//a[contains(.,'Home')]")
    public WebElement homeLink;

//    public WebElement addButton(){return driver.findElement(By.name("add_cart_product"));}


//    public WebElement linkHome(){return driver.findElement(By.xpath("//a[contains(.,'Home')]"));}

    public void waitAddButton(){wait.until(presenceOfElementLocated(By.name("add_cart_product")));}

    public void chooseSize(String size) {
       WebElement selectSizeElem = driver.findElement(By.name("options[Size]"));
        Select selectSize = new Select(selectSizeElem);
        selectSize.selectByValue(size); //Выбрать в списке размеров пункт по значению
    }



}
