package hr.java.shop.demo;

import hr.java.shop.demo.domain.Category;
import hr.java.shop.demo.domain.Item;
import hr.java.shop.demo.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.List;

import static hr.java.shop.demo.utils.FileUtils.saveItems;

public class AddNewItemScreenController {

    @FXML
    private TextField itemNameTextField;

    @FXML
    private ComboBox<String> itemCategoryComboBox;

    @FXML
    private TextField itemWidthTextField;

    @FXML
    private TextField itemHeightTextField;

    @FXML
    private TextField itemLenghtTextField;

    @FXML
    private TextField itemProductionCostTextField;

    @FXML
    private TextField itemSellingPriceTextField;

    @FXML
    private TextField itemDiscountAmountTextField;

    public void initialize(){

        List<Category> categoryList = FileUtils.categoryReader();
        List<String> categoryNameList = categoryList.stream().map(Category::getName).toList();
        ObservableList<String> observableCategoryNameList = FXCollections.observableArrayList(categoryNameList);

        itemCategoryComboBox.getItems().addAll(observableCategoryNameList);

    }

    public void addNewItem(){

        List<Category> categoryList = FileUtils.categoryReader();
        List<Item> itemList = FileUtils.itemReader(categoryList);

        String itemName = itemNameTextField.getText();
        
        Category itemCategory = categoryList.get(0);


        //radi ok
        for (Category category : categoryList) {
            if (category.getName().equals(itemCategoryComboBox.getValue())){
                itemCategory = category;
            }
        }

        BigDecimal itemWidth = new BigDecimal(itemWidthTextField.getText());
        BigDecimal itemHeight = new BigDecimal(itemHeightTextField.getText());
        BigDecimal itemLength = new BigDecimal(itemLenghtTextField.getText());
        BigDecimal itemProductionCost = new BigDecimal(itemProductionCostTextField.getText());
        BigDecimal itemSellingPrice = new BigDecimal(itemSellingPriceTextField.getText());
        BigDecimal itemDiscountAmount = new BigDecimal(itemDiscountAmountTextField.getText());

        Item newItem = new Item(itemName,FileUtils.generateNewItemId(), itemCategory, itemWidth,
                itemHeight,itemLength,itemProductionCost,itemSellingPrice,itemDiscountAmount);

        itemList.add(newItem);

        FileUtils.saveItems(itemList);

    }

}
