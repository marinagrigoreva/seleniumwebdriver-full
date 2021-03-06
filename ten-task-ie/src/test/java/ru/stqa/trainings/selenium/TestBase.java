package ru.stqa.trainings.selenium;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


import java.io.File;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by Марина on 30.04.2017.
 */
public class TestBase {
    public WebDriver driver;
    public WebDriverWait wait;
    public static final String USERNAME = "marinagrigoryeva1";
    public static final String AUTOMATE_KEY = "3xGMvxakZAxhxkDc5L89";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    @Before
    public void start() throws Exception{


        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setPlatform(Platform.WINDOWS);
        capability.setCapability("build", "JUnit - Sample");
        driver = new RemoteWebDriver(
                new URL("https://marinagrigoryeva1:3xGMvxakZAxhxkDc5L89@hub-cloud.browserstack.com/wd/hub"),
                capability
        );
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);





  /*      driver = new RemoteWebDriver(
                new java.net.URL("http://192.168.0.103:4444/wd/hub"),
                DesiredCapabilities.internetExplorer()); */

   /*     driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,30); */
    }

    public boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException ex){
            throw ex;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    public boolean areElementsPresent(By locator){
        return driver.findElements(locator).size()>0;
    }

    //Получить значения R G B
    public String[] getRgb(String str){
        int firstR =str.indexOf("(");
        int lastRr =str.indexOf(",", firstR);
        String r=str.substring(firstR+1, lastRr); //Значение R
        int firstG =str.indexOf(" ");
        int lastG =str.indexOf(",", firstG);
        String g=str.substring(firstG+1, lastG); //Значение G
        int firstB =str.indexOf(" ", lastG);
        int lastB =str.indexOf(",", firstB);
        String b=str.substring(firstB+1, lastB); //Значение B
        String[] rgb ={r,g,b};
        return rgb;


    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
