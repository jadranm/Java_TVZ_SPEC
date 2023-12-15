package hr.java.shop.demo;

import hr.java.shop.demo.domain.Category;
import hr.java.shop.demo.domain.Item;
import hr.java.shop.demo.utils.FileUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

public class ItemSearchScreenController {
    @FXML
    private TextField itemNameTextField;

    //mozda dodati jso jedan

    @FXML
    private TableView<Item>  itemTableView;
    @FXML
    private TableColumn<Item, String> itemNameTableColumn;
    @FXML
    private TableColumn<Item, String> itemCategoryTableColumn;
    @FXML
    private TableColumn<Item, String> itemWidthTableColumn;
    @FXML
    private TableColumn<Item, String> itemHightTableColumn;
    @FXML
    private TableColumn<Item, String> itemLenghtTableColumn;
    @FXML
    private TableColumn<Item, String> itemProductionCostTableColumn;
    @FXML
    private TableColumn<Item, String> itemSellingPriceTableColumn;

    public void initialize(){
        itemNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));

        itemCategoryTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCategory().getName()));

        itemWidthTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty(cellData.getValue().getWidth()));

        itemHightTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty(cellData.getValue().getHeight()));

        itemLenghtTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty(cellData.getValue().getLenght()));

        itemProductionCostTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty(cellData.getValue().getProductionCost()));

        itemSellingPriceTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty(cellData.getValue().getSellingPrice()));

    }

    public void searchItems(){
        List<Category> categoryList = FileUtils.categoryReader();
        List<Item> itemList = FileUtils.itemReader(categoryList);

        if (Optional.ofNullable(itemNameTextField.getText()).isPresent()){
            itemList = itemList.stream()
                    .filter(item -> item.getName().contains(itemNameTextField.getText())).toList();
        }

        ObservableList <Item> observableItemList = FXCollections.observableArrayList(itemList);

        itemTableView.setItems(observableItemList);
    }
}