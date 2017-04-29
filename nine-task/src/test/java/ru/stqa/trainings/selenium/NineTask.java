package ru.stqa.trainings.selenium;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Марина on 28.04.2017.
 */
public class NineTask extends TestBase{


    @Test //Задание 9.1. Тест падает при сравнении сорированного и не сортированного списка стран, т.к. в списке есть страна начинающаяся с Å
    public void SortCountriesZones(){
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin"); //логин
        driver.findElement(By.name("password")).sendKeys("admin"); //пароль
        driver.findElement(By.xpath("//button[@name='login']")).click(); //вход

        wait.until(presenceOfElementLocated(By.xpath("//tr/td/a[contains(.,'Zimbabwe')]")));


        List<WebElement> countryElements= driver.findElements(By.xpath("//tbody/tr/td[5]/a")); //Список элементов стран
        int length = countryElements.size();  //Количество стран на странице

        String[] countryName = new String[length]; //Массив названий стран

        int i=1;
        while (i<=length){

            countryName[i-1] = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[5]/a")).getText();

            //если у страны зон больше 0 то
            if (Integer.parseInt(driver.findElement(By.xpath("//tbody/tr["+i+"]/td[@class='text-center']")).getText())>0){
                driver.findElement(By.xpath("//tbody/tr["+i+"]/td[5]/a")).click(); //Кликнуть по стране

                //Получим список полей с названием зоны
                List<WebElement>zonesElements= driver.findElements(By.xpath("//input[contains(@name,'[name]')]"));
                //Количество зон у страны
                int countZones = zonesElements.size();
                int j=0;
                String[] zonesName = new String[countZones]; // Массив для названий зон
                while (j<countZones){  //Пока j меньше количества зон
                    //Записать название зоны в массив
                    zonesName[j] = zonesElements.get(j).getAttribute("value");
                    j++;
                }
                String[] zonesNameCopy = (String[]) zonesName.clone(); //Создать копию массива названий зон
                Arrays.sort(zonesNameCopy); //Сортировать копию массива
                int k=0;
                while (k<countZones){
                    assertTrue(zonesNameCopy[k]==zonesName[k]); //Сравниваем элементы массивов: сортированной копии и оригинала
                    k++;
                }
                driver.findElement(By.xpath("//button[@name='cancel']")).click(); //Покинуть страницу с знами
            }
            i++;
        }
        String[] countryNameCopy = (String[]) countryName.clone(); //Создать копию массива названий стран
        Arrays.sort(countryNameCopy);
        int l=0;
        while (l<length){
            assertTrue(countryNameCopy[l].equals(countryName[l])); //Сравниваем элементы сортированного и не сортированного массива
            l++;
        }
    }

    @Test //Задание 9.1. Заменяем символ Å на A в названии страны, тест не падает при сравнении сортированного и не сортированного списков
    public void SortCountriesZones2(){
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin"); //логин
        driver.findElement(By.name("password")).sendKeys("admin"); //пароль
        driver.findElement(By.xpath("//button[@name='login']")).click(); //вход

        wait.until(presenceOfElementLocated(By.xpath("//tr/td/a[contains(.,'Zimbabwe')]")));


        List<WebElement> countryElements= driver.findElements(By.xpath("//tbody/tr/td[5]/a")); //Список элементов стран
        int length = countryElements.size();  //Количество стран на странице

        String[] countryName = new String[length]; //Массив названий стран

        int i=1;
        while (i<=length){
            String tz = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[5]/a")).getText();
            countryName[i-1] = tz.replace ('Å', 'A');

            //если у страны зон больше 0 то
            if (Integer.parseInt(driver.findElement(By.xpath("//tbody/tr["+i+"]/td[@class='text-center']")).getText())>0){
                driver.findElement(By.xpath("//tbody/tr["+i+"]/td[5]/a")).click(); //Кликнуть по стране

                //Получим список полей с названием зоны
                List<WebElement>zonesElements= driver.findElements(By.xpath("//input[contains(@name,'[name]')]"));
                //Количество зон у страны
                int countZones = zonesElements.size();
                int j=0;
                String[] zonesName = new String[countZones]; // Массив для названий зон
                while (j<countZones){  //Пока j меньше количества зон
                    //Записать название зоны в массив
                    zonesName[j] = zonesElements.get(j).getAttribute("value");
                    j++;
                }
                String[] zonesNameCopy = (String[]) zonesName.clone(); //Создать копию массива названий зон
                Arrays.sort(zonesNameCopy); //Сортировать копию массива
                int k=0;
                while (k<countZones){
                    assertTrue(zonesNameCopy[k]==zonesName[k]); //Сравниваем элементы массивов: сортированной копии и оригинала
                    k++;
                }
                driver.findElement(By.xpath("//button[@name='cancel']")).click(); //Покинуть страницу с зонами
            }
            i++;
        }
        String[] countryNameCopy = (String[]) countryName.clone(); //Создать копию массива названий стран
        Arrays.sort(countryNameCopy); //Сортировать скопированный массив
        int l=0;
        while (l<length){
            assertTrue(countryNameCopy[l].equals(countryName[l])); //Сравниваем элементы сортированного и не сортированного массива
            l++;
        }
    }


    @Test //Задание 9.2
    public void SortTimeZones(){
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin"); //логин
        driver.findElement(By.name("password")).sendKeys("admin"); //пароль
        driver.findElement(By.xpath("//button[@name='login']")).click(); //вход

        List<WebElement> countryElements= driver.findElements(By.xpath("//tbody/tr/td[3]/a")); //Список элементов стран
        int length = countryElements.size(); //Количество стран
        int i=1;
        while (i<=length) {
            driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[3]/a")).click();
            List<WebElement> tzElements = driver.findElements(By.xpath("//tr/td[3]"));
            //Количество часовых зон у страны
            int countTZ = tzElements.size();

            int j=0;
            String[] tzName = new String[countTZ]; // Массив для названий часовых поясов
            while (j<countTZ){  //Пока j меньше количества часовых поясов
                //Записать название часового пояса в массив
                tzName[j] = tzElements.get(j).getText();
                j++;
            }
            String[] tzNameCopy = (String[]) tzName.clone(); //Создать копию массива названий часовых поясов
            Arrays.sort(tzNameCopy); //Сортировать копию массива
            int k=0;
            while (k<countTZ){
                assertTrue(tzNameCopy[k]==tzName[k]); //Сравниваем элементы массивов: сортированной копии и оригинала
                k++;
            }
            driver.findElement(By.xpath("//button[@name='cancel']")).click(); //Выйти со страницы с часовыми поясами
            i++;
        }


    }

}
