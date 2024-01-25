package commands;

import client.Product;
import server.CollectionController;

import java.io.Serializable;

public class RemoveById implements Command, Serializable {
    private final CollectionController collectionController;

    private final CommandName name = CommandName.REMOVE_BY_ID;

    private String[] params;

    public RemoveById(CollectionController collectionController, String... params) {
        this.collectionController = collectionController;
        this.params = params;
    }

    public CommandName getName() {
        return name;
    }

    @Override
    public void execute() {
        for (Product product : collectionController.getCollection()) {
            if (product.getId().equals(Integer.parseInt(params[0]))) {
                collectionController.getCollection().remove(product);
                System.out.println("Product with id " + product.getId().toString() + " removed");
                break;
            }
        }
        System.out.println("No product with this id");
    }
}
