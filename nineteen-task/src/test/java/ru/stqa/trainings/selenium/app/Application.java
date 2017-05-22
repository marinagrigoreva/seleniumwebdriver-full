package ru.stqa.trainings.selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.trainings.selenium.model.Product;
import ru.stqa.trainings.selenium.pages.CartPage;
import ru.stqa.trainings.selenium.pages.HomePage;
import ru.stqa.trainings.selenium.pages.ProductPage;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by Марина on 22.05.2017.
 */
public class Application {

    private WebDriverWait wait;
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    String url="http://localhost/litecart/en/";

             public Application() {
                 driver = new ChromeDriver();
                 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                 wait = new WebDriverWait(driver, 30);
                 homePage = new HomePage(driver);
                 productPage = new ProductPage(driver);
                 cartPage = new CartPage(driver);

             }

             public void quit() {
                driver.quit();
            }






    public void deleteProducts(int countProducts){
        cartPage.waitDeleteButtons(countProducts); //Дождаться появления трех кнопоку удаления товара
        int j = 0;
        while (j<countProducts){
            //Получить элемент суммы Subtotal
            WebElement sum = cartPage.sumCart();
            cartPage.buttonDelete.click(); //Удалить товар
            wait.until(stalenessOf(sum)); //Дождаться когда элемент суммы изменится
            j++;
        }
    }

    public int addProducts(Product product){

        homePage.open();
        int i =0;
        int countDeleteButton;
        String size = "";
        while (i<product.getCount()){
            String number= getCountItemsInCart();
            int count = Integer.parseInt(number); //Получить из строки количество товаров
            count++;
            String q = Integer.toString(count);
            gotoTab(product);
            chooseProduct(product);
            if((i==0)||(size.equals("Large"))){
                size="Small";
            } else {
                if (size.equals("Small")){
                    size="Medium";
                }else {
                    if (size.equals("Medium")) {
                        size = "Large";
                    }
                }
            }
            productPage.chooseSize(size);
            addToCart(q);
            gotoHome();
            i++;
        }
        if (i<3){
            countDeleteButton=i+1;
        } else {
            countDeleteButton=3;
        }
        return countDeleteButton;
    }


    private String getCountItemsInCart() {
        String quantity = homePage.countItemsInCart.getText(); //Получить количество товаров в корзине

        return quantity;
    }

    private void addToCart(String count){
        productPage.addButton.click(); //Добавить товар в корзину
        //Дождаться когда в корзине станет на один товар больше, чем было
 //       wait.until(presenceOfElementLocated(By.xpath("//span[contains(.,'"+count+"')][@class='quantity']")));
        wait.until(attributeContains(homePage.countItemsInCart, "textContent", count));

    }

    public void gotoCart(){
        homePage.buttonCart.click(); //Перейти в корзину
        wait.until(urlContains("checkout"));
    }


    private void gotoTab(Product product) {
        homePage.tabName(product).click();

    }

    private void gotoHome() {
 //       productPage.linkHome().click(); //Вернуться на главную страницу
        productPage.homeLink.click();
    }

    private void chooseProduct(Product product) {
        homePage.product(product).click(); //Выбрать первый товар на странице
        productPage.waitAddButton(); //Дождаться появления кнопки добавления

    }


}
