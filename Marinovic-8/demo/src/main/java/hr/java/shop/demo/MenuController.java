package hr.java.shop.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuController {
    public void showItemSearchScreen(){;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ItemSearchScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainApplication.getMainStage().setTitle("Item search");
        MainApplication.getMainStage().setScene(scene);
        MainApplication.getMainStage().show();
    }
    public void showCategorySearchScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("categorySearchScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainApplication.getMainStage().setTitle("Category search");
        MainApplication.getMainStage().setScene(scene);
        MainApplication.getMainStage().show();

    }

    public void showFactorySearchScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("factorySearchScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainApplication.getMainStage().setTitle("Factory search");
        MainApplication.getMainStage().setScene(scene);
        MainApplication.getMainStage().show();

    }

    public void showStoreSearchScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("storeSearchScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainApplication.getMainStage().setTitle("Store search");
        MainApplication.getMainStage().setScene(scene);
        MainApplication.getMainStage().show();

    }

    public void showAddNewItemScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addNewItemScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainApplication.getMainStage().setTitle("Add new Item");
        MainApplication.getMainStage().setScene(scene);
        MainApplication.getMainStage().show();

    }

    public void showAddNewCategoryScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addNewCategoryScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainApplication.getMainStage().setTitle("Add new Category");
        MainApplication.getMainStage().setScene(scene);
        MainApplication.getMainStage().show();

    }

    public void showAddNewFactoryScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addNewFactoryScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainApplication.getMainStage().setTitle("Add new Factory");
        MainApplication.getMainStage().setScene(scene);
        MainApplication.getMainStage().show();

    }

    public void showAddNewStoreScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addNewStoreScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainApplication.getMainStage().setTitle("Add new Store");
        MainApplication.getMainStage().setScene(scene);
        MainApplication.getMainStage().show();

    }
}
