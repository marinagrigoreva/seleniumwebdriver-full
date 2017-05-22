package ru.stqa.trainings.selenium.model;

/**
 * Created by Марина on 21.05.2017.
 */
public class Product {

    private String tabName;
    private String productName;
    private int count;


    public static Builder newEntity() {
        return new Product().new Builder();
    }

    public String getTabName() {
        return tabName;
    }

    public String getProductName() {
        return productName;
    }

    public int getCount() {
        return count;
    }



    public class Builder {
        private Builder() {
        }

        public Builder withTabName(String tabName) {
            Product.this.tabName = tabName;
            return this;
        }

        public Builder withProductName(String productName) {
            Product.this.productName = productName;
            return this;
        }


        public Builder withCount(int count) {
            Product.this.count = count;
            return this;
        }



        public Product build() {
            return Product.this;
        }


    }
}