package ru.stqa.trainings.selenium.tests;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.stqa.trainings.selenium.model.Product;

import static org.junit.Assert.assertTrue;


/**
 * Created by Марина on 10.05.2017.
 */
@RunWith(DataProviderRunner.class)
public class NineteenTask extends TestBase{




    @Test
    @UseDataProvider(value = "validProducts", location = DataProviders.class)
    public void addToCart(Product product){
   /*     Product buy = new Product().newEntity()
                .withCount(4).withProductName("Yellow Duck").withTabName("Campaign Products").build(); */
        int countProducts =app.addProducts(product);
        app.gotoCart();
        app.deleteProducts(countProducts);



    }


}
