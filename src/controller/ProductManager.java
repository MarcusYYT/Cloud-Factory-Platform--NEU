package controller;

import entity.Product;
import util.FileOperator;

import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 18:53
 */
public class ProductManager {
    private  List<Product> products;
    private static ProductManager singletonInstance;

    private ProductManager() {
        products = FileOperator.loadData("Products.json", Product.class);
    }

    // 实现单例模式
    public static ProductManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ProductManager();
        }
        return singletonInstance;
    }

    public void addProduct(Product product) {
        products.add(product);
        FileOperator.writeData(product, "Products.json");
    }

    public void delProduct(Product product) {
        products.remove(product);
        FileOperator.writeData(products, "Products.json");
    }

    public List<Product> getProducts() {
        return products;
    }
}
