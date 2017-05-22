package ru.stqa.trainings.selenium.tests;
import com.tngtech.java.junit.dataprovider.DataProvider;
import ru.stqa.trainings.selenium.model.Product;

/**
 * Created by Марина on 22.05.2017.
 */
public class DataProviders {

    @DataProvider
    public static Object[][] validProducts(){
        return  new Object[][]{
                { Product.newEntity()
                    .withCount(3).withProductName("Yellow Duck").withTabName("Campaign Products").build()},

        };
    }



}
