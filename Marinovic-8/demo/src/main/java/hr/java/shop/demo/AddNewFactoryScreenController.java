package hr.java.shop.demo;

import hr.java.shop.demo.domain.Address;
import hr.java.shop.demo.domain.Category;
import hr.java.shop.demo.domain.Factory;
import hr.java.shop.demo.domain.Item;
import hr.java.shop.demo.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddNewFactoryScreenController {

    @FXML
    private TextField factoryNameTextField;

    @FXML
    private ComboBox<String> factoryAddressComboBox;

    @FXML
    private TextField factoryItemsTextField;

    public void initialize(){

        List<Address> addressList = FileUtils.addressReader();

        List<String> addressNameList = addressList.stream().map(Address::getStreet).toList();
        ObservableList<String> observableAddressList = FXCollections.observableArrayList(addressNameList);

        factoryAddressComboBox.getItems().addAll(observableAddressList);
    }
    public void addNewFactory(){

        List<Address> addressList = FileUtils.addressReader();
        List<Category> categoryList = FileUtils.categoryReader();
        List<Item> itemList = FileUtils.itemReader(categoryList);
        List<Factory> factoryList = FileUtils.factoryReader(itemList);

        String factoryName = factoryNameTextField.getText();

        String itemsInFactoryString = factoryItemsTextField.getText();

        List<Integer> itemsIndexList = Arrays.stream(itemsInFactoryString.split("[,\\s.]+"))
                .map(Integer::parseInt)
                .toList();

        List<Item> chosenItemsList = new ArrayList<>();

        for (Integer index : itemsIndexList){
            if (index.equals(0)) {

            }else {
                chosenItemsList.add(itemList.get(index - 1));
            }
        }

        Integer chosenAddressIndex = null;

        for (int i = 0; i < addressList.size(); i++) {
            if (addressList.get(i).getStreet().equals(factoryAddressComboBox.getValue())){
                chosenAddressIndex = i;
            }
        }

        Factory newFactory = new Factory(factoryName,FileUtils.generateNewFactoryId(),addressList.get(chosenAddressIndex),chosenItemsList);
        factoryList.add(newFactory);
        FileUtils.saveFactories(factoryList);

    }
}
