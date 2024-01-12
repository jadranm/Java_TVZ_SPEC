package hr.java.shop.demo;

import hr.java.shop.demo.domain.Category;
import hr.java.shop.demo.domain.Factory;
import hr.java.shop.demo.domain.Item;
import hr.java.shop.demo.utils.FileUtils;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.List;
import java.util.Optional;


public class FactorySearchScreenController {

    @FXML
    private TextField factoryNameTextField;

    @FXML
    private TableView<Factory> factoryTableView;
    @FXML
    private TableColumn<Factory, String> factoryNameTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryAddressTableColumn;
    @FXML
    private TableColumn<Factory, String> itemsInFactoryNameTableColumn;


    public void initialize() {
        factoryNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));

        factoryAddressTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAddress().getStreet()));

        itemsInFactoryNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getItems().stream().map(Item::getName)
                                .collect(Collectors.joining(", "))));


    }

    public void searchItems() {
        List<Category> categoryList = FileUtils.categoryReader();
        List<Item> itemList = FileUtils.itemReader(categoryList);
        List<Factory> factoryList = FileUtils.factoryReader(itemList);

        if (Optional.ofNullable(factoryNameTextField.getText()).isPresent()) {
            factoryList = factoryList.stream()
                    .filter(factory -> factory.getName().contains(factoryNameTextField.getText())).toList();
        }


        ObservableList<Factory> observableFactoryList = FXCollections.observableArrayList(factoryList);

        factoryTableView.setItems(observableFactoryList);
    }
}

