package hr.java.production.main;

import hr.java.production.model.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Integer NUMBER_OF_CATEGORIES = 3;
    private static final Integer NUMBER_OF_ITEMS = 5;
    private static final Integer NUMBER_OF_FACTORIES = 2;
    private static final Integer NUMBER_OF_ITEMS_PER_FACTORY = 2;
    private static final Integer NUMBER_OF_STORES = 2;
    private static final Integer NUMBER_OF_ITEMS_PER_STORE = 2;


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Category[] categoryList = new Category[NUMBER_OF_CATEGORIES];
        Item[] itemList = new Item[NUMBER_OF_ITEMS];
        Item[] chosenItemsList = new Item[NUMBER_OF_ITEMS_PER_FACTORY];
        Factory[] factoriesList = new Factory[NUMBER_OF_FACTORIES];
        Store[] storeList = new Store[NUMBER_OF_STORES];
        Item[] chosenStoreItemsList = new Item[NUMBER_OF_ITEMS_PER_FACTORY];

        //radi ok
        categoryInput(input, categoryList);


        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {

            System.out.print("unesi ime predmeta: ");
            String itemName = input.next();

            System.out.print("Odaberite kategoriju (1-" + NUMBER_OF_CATEGORIES + "): ");
            int categoryIndex = input.nextInt();
            Category selectedCategory = categoryList[categoryIndex - 1];

            System.out.print("unesi sirinu predmeta: ");
            BigDecimal itemWidth = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi visinu predmeta: ");
            BigDecimal itemHeight = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi duzinu predmeta: ");
            BigDecimal itemLenght = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi trosak proizvodnje: ");
            BigDecimal itemProductionCost = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi cijenu: ");
            BigDecimal itemSellingPrice = input.nextBigDecimal();
            input.nextLine();

            Item newItem = new Item(itemName, selectedCategory, itemWidth, itemHeight, itemProductionCost, itemSellingPrice);
            itemList[i] = newItem;
        }


        for (int i = 0; i < NUMBER_OF_FACTORIES; i++) {

            System.out.print("unesi ime tvornice: ");
            String factoryName = input.next();

            Address factoryAddress = addressInput(input);

            for (int j = 0; j < NUMBER_OF_ITEMS_PER_FACTORY; j++) {
                System.out.print("odaberi " + (j + 1) + " item koji se proizvodi u tvornici");
                Integer chosenItemIndex = input.nextInt();
                chosenItemsList[j] = itemList[chosenItemIndex];
            }

            Factory newFactory = new Factory(factoryName, factoryAddress, chosenItemsList);
            factoriesList[i] = newFactory;


        }

        for (int i = 0; i < NUMBER_OF_STORES; i++) {

            System.out.print("unesi ime ducana: ");
            String storeName = input.next();

            System.out.print("unesi web adresu ducana: ");
            String storeWebAddress = input.next();


            for (int j = 0; j < NUMBER_OF_ITEMS_PER_STORE; j++) {
                System.out.print("odaberi " + (j + 1) + " item koji se prodaje u ducanu");
                Integer chosenItemIndex = input.nextInt();
                chosenItemsList[j] = itemList[chosenItemIndex];
            }

            Store newStore = new Store(storeName, storeWebAddress, chosenStoreItemsList);
            storeList[i] = newStore;

        }

        BigDecimal maxVolume = null;
        Integer maxVolumeIndex;

        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            BigDecimal itemVolume = itemList[i].getWidth().multiply(itemList[i].getHeight()).multiply(itemList[i].getLenght());
            String itemName = itemList[i].getName();

            if (itemVolume.compareTo(maxVolume) > 0) {
                maxVolume = itemVolume;
                maxVolumeIndex = i;
            }
        }
        System.out.print("");

    }

    //kraj programa

    //funkcije
    private static Address addressInput(Scanner input) {
        System.out.print("unesi adresu tvornice: ");
        System.out.print("unesi ime ulice: ");
        String factoryStreet = input.next();

        System.out.print("unesi kucni broj: ");
        String factoryHouseNumber = input.next();

        System.out.print("unesi grad u kojem je tvornica: ");
        String factoryCity = input.next();

        System.out.print("unesi ime ulice: ");
        String factoryPostalCode = input.next();

        Address newAddress = new Address(factoryStreet, factoryHouseNumber, factoryCity, factoryPostalCode);
        return newAddress;
    }

    private static Category[] categoryInput(Scanner input, Category[] categoryList) {

        for (int i = 0; i < NUMBER_OF_CATEGORIES; i++) {
            System.out.print("unesi ime kategorije: ");
            String categoryName = input.next();
            input.nextLine();

            System.out.print("unesi opis kategorije: ");
            String categoryDescription = input.next();
            input.nextLine();

            Category newCategory = new Category(categoryName, categoryDescription);
            categoryList[i] = newCategory;
        }
        return categoryList;
    }
}

