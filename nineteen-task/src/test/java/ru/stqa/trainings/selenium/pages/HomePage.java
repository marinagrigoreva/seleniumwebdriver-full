package ru.stqa.trainings.selenium.pages;

/**
 * Created by Марина on 22.05.2017.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.stqa.trainings.selenium.model.Product;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(){ driver.get("http://localhost/litecart/en/");}

    @FindBy(css="span.quantity")
    public WebElement countItemsInCart;

    @FindBy(xpath="//a[contains(@href,'checkout')]")
    public WebElement buttonCart;

//    public WebElement countItemsInCart(){return driver.findElement(By.cssSelector("span.quantity"));}

//    public WebElement buttonCart(){return driver.findElement(By.xpath("//a[contains(@href,'checkout')]"));}

    public WebElement tabName(Product product){return driver.findElement(By.xpath("//a[contains(.,'"+product.getTabName()+"')]"));}

    public WebElement product(Product product){return driver.findElement(By.xpath("//div[not(contains(@style,'display: none;'))][@class='tab-pane fade in']//a[@title='"+product.getProductName()+"']"));}



}
