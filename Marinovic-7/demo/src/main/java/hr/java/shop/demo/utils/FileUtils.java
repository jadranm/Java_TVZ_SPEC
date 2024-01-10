package hr.java.shop.demo.utils;

import hr.java.shop.demo.domain.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static List<Category> categoryReader() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/categories.txt"))) {

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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/items.txt"))) {

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

        try (BufferedReader factoryReader = new BufferedReader(new FileReader("dat/factories.txt"));
             BufferedReader addressReader = new BufferedReader(new FileReader("dat/addresses.txt"))) {

            List<Factory> factoryList = new ArrayList<>();
            String line;

            while ((line = factoryReader.readLine()) != null) {

                String id = line.trim();
                String name = factoryReader.readLine().trim();
                Integer factoryId = Integer.parseInt(factoryReader.readLine().trim());

                List<String> chosenItemsListIndex = List.of(factoryReader.readLine().trim().split(","));
                List<Item> chosenItemsList = new ArrayList<>();


                //mozda je greska
                for (String chosenItemIndex : chosenItemsListIndex) {
                    chosenItemsList.add(itemList.get(Integer.parseInt(chosenItemIndex) - 1));
                }

                String street = addressReader.readLine().trim();
                String houseNumber = addressReader.readLine().trim();
                String city = addressReader.readLine().trim();
                String postalCode = addressReader.readLine().trim();


                Factory newFactory = new Factory(name, factoryId, new Address(street, houseNumber, city, postalCode), chosenItemsList);
                factoryList.add(newFactory);
            }
            return factoryList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Store> storeReader(List<Item> itemList) {

        try (BufferedReader reader = new BufferedReader(new FileReader("dat/stores.txt"))) {
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
}
