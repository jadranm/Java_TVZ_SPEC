package hr.java.shop.demo.utils;

import hr.java.shop.demo.domain.*;
import javafx.animation.FadeTransition;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FileUtils {

    private static final String ITEM_FILE_LOCATION = "dat/items.txt";

    private static final String CATEGORY_FILE_LOCATION = "dat/categories.txt";

    private static final String ADDRESS_FILE_LOCATION = "dat/addresses.txt";

    private static final String FACTORY_FILE_LOCATION = "dat/factories.txt";

    private static final String STORE_FILE_LOCATION = "dat/stores.txt";

    public static List<Category> categoryReader() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CATEGORY_FILE_LOCATION))) {

            List<Category> categoryList = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null){

                Integer id = Integer.parseInt(line);
                String name = bufferedReader.readLine();
                String description = bufferedReader.readLine();

                Category newCategory = new Category(name,id,description);
                categoryList.add(newCategory);
            }

            return categoryList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Item> itemReader(List<Category> categoryList) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ITEM_FILE_LOCATION))) {

            List<Item> itemList = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null){
                String name = line.trim();

                line = bufferedReader.readLine();
                Integer id = Integer.parseInt(line.trim());
                Integer categoryIndex = Integer.parseInt(bufferedReader.readLine());
                BigDecimal width = new BigDecimal(bufferedReader.readLine());
                BigDecimal height = new BigDecimal(bufferedReader.readLine());
                BigDecimal length = new BigDecimal(bufferedReader.readLine());
                BigDecimal productionCost = new BigDecimal(bufferedReader.readLine());
                BigDecimal sellingPrice = new BigDecimal(bufferedReader.readLine());
                BigDecimal discountAmount = new BigDecimal(bufferedReader.readLine());

                Item newItem = new Item(name, id, categoryList.get(categoryIndex - 1), width, height, length, productionCost, sellingPrice, discountAmount);
                itemList.add(newItem);

            }
            return itemList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Factory> factoryReader(List<Item> itemList) {

        List<Address> addressList = addressReader();

        try (BufferedReader factoryReader = new BufferedReader(new FileReader(FACTORY_FILE_LOCATION))) {

            List<Factory> factoryList = new ArrayList<>();
            String line;

            while ((line = factoryReader.readLine()) != null) {

                Integer id = Integer.valueOf(line);
                String name = factoryReader.readLine();
                Integer addressIndex = Integer.parseInt(factoryReader.readLine()) - 1;


                List<String> chosenItemsListIndex = List.of(factoryReader.readLine().trim().split(","));
                List<Item> chosenItemsList = new ArrayList<>();


                for (String chosenItemIndex : chosenItemsListIndex) {
                    chosenItemsList.add(itemList.get(Integer.parseInt(chosenItemIndex) - 1));
                }

                Factory newFactory = new Factory(name, id, addressList.get(addressIndex), chosenItemsList);
                factoryList.add(newFactory);
            }

            return factoryList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Address> addressReader() {
        try (BufferedReader addressReader = new BufferedReader(new FileReader(ADDRESS_FILE_LOCATION))) {

            List<Address> addressList = new ArrayList<>();
            String line;

            while ((line = addressReader.readLine()) != null) {

                String street = line;
                String houseNumber = addressReader.readLine();
                String city = addressReader.readLine();
                String postalCode = addressReader.readLine();


                Address address = new Address(street, houseNumber, city, postalCode);
                addressList.add(address);
            }
            return addressList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Store> storeReader(List<Item> itemList) {

        try (BufferedReader reader = new BufferedReader(new FileReader(STORE_FILE_LOCATION))) {
            String line;
            List<Store> storeList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {

                String name = line;
                Integer id = Integer.parseInt(reader.readLine().trim());
                String webAddress = reader.readLine();

                List<String> itemIds = Arrays.asList(reader.readLine().trim().split(","));
                List<Item> items = new ArrayList<>();

                for (String itemId : itemIds) {
                    int itemIdInt = Integer.parseInt(itemId.trim());
                    items.add(itemList.get(itemIdInt - 1));
                }

                Store store = new Store(name, id, webAddress, items);
                storeList.add(store);
            }
            return storeList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveItems(List<Item> itemList) {
        List<Category> categoryList = FileUtils.categoryReader();
        try(PrintWriter pw = new PrintWriter(new FileWriter(ITEM_FILE_LOCATION))) {

            for (Item item : itemList){
                pw.println(item.getName());
                pw.println(item.getId());

                for (Category category : categoryList){
                    if (category.equals(item.getCategory())){

                    }
                }
                Integer itemCategoryIndex = 0;

                for (int i = 0; i < categoryList.size(); i++) {
                    if (categoryList.get(i).equals(item.getCategory())){
                        itemCategoryIndex = i;
                    }
                }
                pw.println(itemCategoryIndex + 1);
                pw.println(item.getWidth());
                pw.println(item.getHeight());
                pw.println(item.getLenght());
                pw.println(item.getProductionCost());
                pw.println(item.getSellingPrice());
                pw.println(item.getDiscountAmount());
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void saveCategories(List<Category> categoryList){
        try(PrintWriter pw = new PrintWriter(new FileWriter(CATEGORY_FILE_LOCATION))) {

            for (Category category : categoryList){

                pw.println(category.getId());
                pw.println(category.getName());
                pw.println(category.getDescription());
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void saveFactories(List<Factory> factoryList){

        List<Category> categoryList = categoryReader();
        List<Item> itemList = itemReader(categoryList);
        List<Address> addressList = addressReader();
        

        try(PrintWriter pw = new PrintWriter(new FileWriter(FACTORY_FILE_LOCATION))) {

            for (Factory factory : factoryList){

                pw.println(factory.getId());
                pw.println(factory.getName());
                Integer chosenAddressIndex = null;
                
                for (int i = 0; i < addressList.size(); i++) {
                    if (addressList.get(i).equals(factory.getAddress())){
                        chosenAddressIndex = i;
                    }
                }
                pw.println(chosenAddressIndex + 1);


                List<Item> chosenItems = factory.getItems();
                List<Integer> chosenItemsIndexList = new ArrayList<>();

                for (int i = 0; i < itemList.size(); i++) {
                    for (int j = 0; j < chosenItems.size(); j++) {
                        if (itemList.get(i).equals(chosenItems.get(j))){
                            chosenItemsIndexList.add(i + 1);
                        }
                    }
                }

                StringJoiner stringJoiner = new StringJoiner(",");
                for (Integer number : chosenItemsIndexList) {
                    stringJoiner.add(number.toString());
                }
                String resultUsingStringJoiner = stringJoiner.toString();

                pw.println(resultUsingStringJoiner);
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void saveStores(List<Store> storeList) {

        List<Category> categoryList = categoryReader();
        List<Item> itemList = itemReader(categoryList);



        try(PrintWriter pw = new PrintWriter(new FileWriter(STORE_FILE_LOCATION))) {

            for (Store store : storeList){

                pw.println(store.getName());
                pw.println(store.getId());
                pw.println(store.getWebAddress());

                List<Item> chosenItems = store.getItems();
                List<Integer> chosenItemsIndexList = new ArrayList<>();

                for (int i = 0; i < itemList.size(); i++) {
                    for (int j = 0; j < chosenItems.size(); j++) {
                        if (itemList.get(i).equals(chosenItems.get(j))){
                            chosenItemsIndexList.add(i + 1);
                        }
                    }
                }

                StringJoiner stringJoiner = new StringJoiner(",");
                for (Integer number : chosenItemsIndexList) {
                    stringJoiner.add(number.toString());
                }
                String resultUsingStringJoiner = stringJoiner.toString();

                pw.println(resultUsingStringJoiner);
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Integer generateNewItemId(){
        List<Category> categoryList = FileUtils.categoryReader();

        Optional<Item> itemOptional = itemReader(categoryList).stream().max((i1, i2) -> i1.getId().compareTo(i2.getId()));

        if (itemOptional.isPresent()){
            return itemOptional.get().getId() + 1;
        }else {
            return 1;
        }
    }

    public static Integer generateNewCategoryId(){
        Optional<Category> categoryOptional = categoryReader().stream().max((c1, c2) -> c1.getId().compareTo(c2.getId()));

        if (categoryOptional.isPresent()){
            return categoryOptional.get().getId() + 1;
        }else {
            return 1;
        }
    }

    public static Integer generateNewFactoryId(){
        List<Category> categoryList = FileUtils.categoryReader();
        List<Item> itemList = FileUtils.itemReader(categoryList);

        Optional<Factory> factoryOptional = factoryReader(itemList).stream().max((f1, f2) -> f1.getId().compareTo(f2.getId()));

        if (factoryOptional.isPresent()){
            return factoryOptional.get().getId() + 1;
        }else {
            return 1;
        }
    }

    public static Integer generateNewStoreId(){
        List<Category> categoryList = FileUtils.categoryReader();
        List<Item> itemList = FileUtils.itemReader(categoryList);

        Optional<Store> storeOptional = storeReader(itemList).stream().max((s1, s2) -> s1.getId().compareTo(s2.getId()));

        if (storeOptional.isPresent()){
            return storeOptional.get().getId() + 1;
        }else {
            return 1;
        }
    }


}
