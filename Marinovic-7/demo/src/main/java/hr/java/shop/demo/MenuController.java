package hr.java.shop.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuController {
    public void showItemSearchScreen(){;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ItemSearchScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HelloApplication.getMainStage().setTitle("Item search");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showCategorySearchScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categorySearchScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.<Parent>load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HelloApplication.getMainStage().setTitle("Category search");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }
}
