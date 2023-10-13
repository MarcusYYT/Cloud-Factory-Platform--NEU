package entity;

import util.FileOperator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-20 22:03
 */
public class ProductType {
    private List<Type> types;
    private static ProductType singletonInstance;

    // 实现单例模式：只有一个ProductType被创建
    public static ProductType getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ProductType();
        }
        return singletonInstance;
    }
    private ProductType() {
        types = FileOperator.loadData("ProductTypes.json", Type.class);
    }

    public void addType(String type) {
        Type t = new Type(type);
        types.add(t);
        FileOperator.writeData(t, "ProductTypes.json");
    }

    public void removeType(Type type) {
        try {
            types.remove(type);
        } catch (Exception e) {
        }
        FileOperator.writeData(types, "ProductTypes.json");
    }

    public List<Type> getTypes() {
        return types;
    }
}

