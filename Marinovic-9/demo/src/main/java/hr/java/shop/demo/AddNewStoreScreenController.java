package hr.java.shop.demo;

import hr.java.shop.demo.domain.Category;
import hr.java.shop.demo.domain.Item;
import hr.java.shop.demo.domain.Store;
import hr.java.shop.demo.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddNewStoreScreenController {

    @FXML
    private TextField storeNameTextField;

    @FXML
    private TextField storeWebAddressTextField;

    @FXML
    private TextField storeItemsTextField;

    public void initialize(){

    }
    public void addNewStore(){

        List<Category> categoryList = FileUtils.categoryReader();
        List<Item> itemList = FileUtils.itemReader(categoryList);
        List<Store> storeList = FileUtils.storeReader(itemList);

        String storeName = storeNameTextField.getText();
        String storeWebAddress = storeWebAddressTextField.getText();
        String storeItemsString = storeItemsTextField.getText();

        List<Integer> itemsIndexList = Arrays.stream(storeItemsString.split("[,\\s.]+"))
                .map(Integer::parseInt)
                .toList();

        List<Item> chosenItemsList = new ArrayList<>();

        for (Integer index : itemsIndexList){
            if (index.equals(0)) {

            }else {
                chosenItemsList.add(itemList.get(index - 1));
            }
        }

        Store newStore = new Store(storeName,FileUtils.generateNewStoreId(),storeWebAddress,chosenItemsList);
        storeList.add(newStore);

        FileUtils.saveStores(storeList);

    }
}
