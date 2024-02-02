package commands;

import client.Product;
import server.CollectionController;
import utils.Validator;

import java.awt.desktop.ScreenSleepEvent;
import java.io.Serializable;
import java.util.Scanner;

public class AddCommand implements Command, Serializable {
    private final CommandName name = CommandName.ADD;
    private CollectionController collectionController;
    private Scanner scanner;

    private String[] params;

    public CommandName getName() {
        return name;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public AddCommand(CollectionController collectionController, Scanner scanner) {
        this.collectionController = collectionController;
        this.scanner = scanner;
    }

    public String getDescription() {
        return "Insert product to collection";
    }

    public String execute() {
        Product newProduct = createNewProduct();
        if (newProduct != null) {
            collectionController.getCollection().add(newProduct);
            return "New product added";
        }
        return "Something went wrong. Product hasn't been added";
    }

    private Product createNewProduct() {
        System.out.println("Enter name of new product:");
        String name;
        if (this.scanner.hasNext()) {
            name = scanner.nextLine();
            if (Validator.validateCommandName(name)) {
                return new Product(name);
            } else {
                System.out.println("Invalid command name");
                return null;
            }
        }
        return null;
    }


}
