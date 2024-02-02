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

    public String getDescription() {
        return "Remove product by id";
    }


    public void setParams(String[] params) {
        this.params = params;
    }

    public CommandName getName() {
        return name;
    }

    @Override
    public String execute() throws NumberFormatException {
        try {
            for (Product product : collectionController.getCollection()) {
                if (product.getId().equals(Integer.parseInt(params[0]))) {
                    collectionController.getCollection().remove(product);
                    return "Product with id " + product.getId().toString() + " removed";
                }
            }
            return "No products in list";
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }

    }
}
