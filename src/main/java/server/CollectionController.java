package server;

import client.Product;

import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeSet;

public final class CollectionController implements Serializable {
    private static CollectionController INSTANCE;

    private CollectionController() {}

    public static CollectionController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CollectionController();
        }
        return INSTANCE;

    }
    private TreeSet<Product> collection = new TreeSet<>();

    public TreeSet<Product> getCollection() {
        return collection;
    }

    public void addProduct(Product product) {
        collection.add(product);
    }


}
