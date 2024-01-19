package hr.java.shop.demo;

import hr.java.shop.demo.domain.Category;
import hr.java.shop.demo.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;

public class AddNewCategoryScreenController {

    @FXML
    private TextField categoryNameTextField;

    @FXML
    private TextField categoryDescriptionTextField;


    public void initialize(){

    }
    public void addNewCategory(){
        List<Category> categoryList = FileUtils.categoryReader();

        String categoryName = categoryNameTextField.getText();
        String categoryDescription = categoryDescriptionTextField.getText();

        Category newCategory = new Category(categoryName,FileUtils.generateNewCategoryId(),categoryDescription);
        categoryList.add(newCategory);

        FileUtils.saveCategories(categoryList);
    }
}
