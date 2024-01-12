package hr.java.shop.demo;

import hr.java.shop.demo.domain.Category;
import hr.java.shop.demo.domain.Item;
import hr.java.shop.demo.domain.Store;
import hr.java.shop.demo.utils.FileUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StoreSearchScreenController {

    @FXML
    private TextField storeNameTextField;

    @FXML
    private TableView<Store> storeTableView;
    @FXML
    private TableColumn<Store, String> storeNameTableColumn;
    @FXML
    private TableColumn<Store, String> storeWebAddressTableColumn;
    @FXML
    private TableColumn<Store, String> storeItemsTableColumn;


    public void initialize() {
        storeNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));

        storeWebAddressTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getWebAddress()));

        storeItemsTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getItems().stream().map(Item::getName)
                        .collect(Collectors.joining(", "))));


    }

    public void searchItems() {
        List<Category> categoryList = FileUtils.categoryReader();
        List<Item> itemList = FileUtils.itemReader(categoryList);
        List<Store> storeList = FileUtils.storeReader(itemList);

        if (Optional.ofNullable(storeNameTextField.getText()).isPresent()) {
            storeList = storeList.stream()
                    .filter(store -> store.getName().contains(storeNameTextField.getText())).toList();
        }


        ObservableList<Store> observableFactoryList = FXCollections.observableArrayList(storeList);

        storeTableView.setItems(observableFactoryList);
    }
}
