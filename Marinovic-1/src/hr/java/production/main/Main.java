package hr.java.production.main;

import hr.java.production.model.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Integer NUMBER_OF_CATEGORIES = 1;
    private static final Integer NUMBER_OF_ITEMS = 2;
    private static final Integer NUMBER_OF_FACTORIES = 2;
    private static final Integer NUMBER_OF_STORES = 2;


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("koliko itema proizvodi svaka tvornica: ");
        Integer numberOfItemsPerFactory = input.nextInt();

        System.out.print("koliko itema ima svaki ducan: ");
        Integer numberOfItemsPerStore = input.nextInt();

        Category[] categoryArray = new Category[NUMBER_OF_CATEGORIES];
        Item[] itemArray = new Item[NUMBER_OF_ITEMS];
        Factory[] factoriesArray = new Factory[NUMBER_OF_FACTORIES];
        Store[] storeArray = new Store[NUMBER_OF_STORES];

        Item[] chosenStoreItemsArray = new Item[numberOfItemsPerStore];
        Item[] chosenItemsArray = new Item[numberOfItemsPerFactory];
        //radi ok
        categoryInput(input, categoryArray);

        itemInput(input, categoryArray, itemArray);


        factoriesInput(input, numberOfItemsPerFactory, chosenItemsArray, itemArray, factoriesArray);

        storesInput(input, numberOfItemsPerStore, chosenItemsArray, itemArray, chosenStoreItemsArray, storeArray);

        //svi upisi su ok
//lenght ne length

        //actual logika programa

        BigDecimal maxVolume = null;
        Integer maxItemVolumeIndex = null;
        Integer maxFactoryVolumeIndex = null;


        for (int i = 0; i < factoriesArray.length; i++) {
            Item[] newItem = factoriesArray[i].getItems();

            for (int j = 0;j < newItem.length; j++){

                BigDecimal itemVolume = newItem[j].getWidth().multiply(newItem[j].getHeight()).multiply(newItem[j].getLength());
                //izracun volumena radi

                if (maxVolume == null || itemVolume.compareTo(maxVolume) > 0) {
                    maxVolume = itemVolume;
                    maxFactoryVolumeIndex = i;
                    maxItemVolumeIndex = j;
                }
            }

        }
        System.out.println("najveci volumen " + maxVolume);
        System.out.println("proizvela je tvornica " + factoriesArray[maxFactoryVolumeIndex].getName());

    }

    //kraj programa

    //funkcije
    private static void storesInput(Scanner input, Integer numberOfItemsPerStore, Item[] chosenItemsArray, Item[] itemArray, Item[] chosenStoreItemsArray, Store[] storeArray) {
        for (int i = 0; i < NUMBER_OF_STORES; i++) {

            System.out.print("unesi ime ducana: ");
            String storeName = input.next();

            System.out.print("unesi web adresu ducana: ");
            String storeWebAddress = input.next();


            for (int j = 0; j < numberOfItemsPerStore; j++) {
                System.out.print("odaberi " + (j + 1) + " item koji se prodaje u ducanu: ");
                int chosenItemIndex = input.nextInt();
                chosenItemsArray[j] = itemArray[chosenItemIndex - 1];
            }

            Store newStore = new Store(storeName, storeWebAddress, chosenStoreItemsArray);
            storeArray[i] = newStore;

        }
    }

    private static void factoriesInput(Scanner input, Integer numberOfItemsPerFactory, Item[] chosenItemsArray, Item[] itemArray, Factory[] factoriesArray) {
        for (int i = 0; i < NUMBER_OF_FACTORIES; i++) {

            System.out.print("unesi ime tvornice: ");
            String factoryName = input.next();

            Address factoryAddress = addressInput(input);

            for (int j = 0; j < numberOfItemsPerFactory; j++) {
                System.out.print("odaberi " + (j + 1) + ". item koji se proizvodi u tvornici: ");
                int chosenItemIndex = input.nextInt();
                chosenItemsArray[j] = itemArray[chosenItemIndex - 1];
            }

            Factory newFactory = new Factory(factoryName, factoryAddress, chosenItemsArray);
            factoriesArray[i] = newFactory;


        }
    }

    private static void itemInput(Scanner input, Category[] categoryArray, Item[] itemArray) {
        int categoryIndex;
        BigDecimal itemWidth;
        BigDecimal itemHeight;
        BigDecimal itemLenght;
        BigDecimal itemProductionCost;
        BigDecimal itemSellingPrice;


        int i = 0;

        do {
            System.out.print("unesi ime [" + (i + 1) + ".] predmeta: ");
            String itemName = input.next();

            System.out.print("Odaberite kategoriju (1-" + NUMBER_OF_CATEGORIES + "): ");
            categoryIndex = input.nextInt();
            Category selectedCategory = categoryArray[categoryIndex - 1];

            System.out.print("unesi sirinu predmeta: ");
            itemWidth = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi visinu predmeta: ");
            itemHeight = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi duzinu predmeta: ");
            itemLenght = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi trosak proizvodnje: ");
            itemProductionCost = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi cijenu: ");
            itemSellingPrice = input.nextBigDecimal();
            input.nextLine();

            Item newItem = new Item(itemName, selectedCategory, itemWidth, itemHeight,itemLenght, itemProductionCost, itemSellingPrice);
            itemArray[i] = newItem;

            i++;
        }while (i < NUMBER_OF_ITEMS);
    }


    private static Address addressInput(Scanner input) {
        System.out.println("unesi adresu tvornice ?>>>>>>>>>>>>>>>");

        System.out.print("unesi ime ulice: ");
        String factoryStreet = input.next();

        System.out.print("unesi kucni broj: ");
        String factoryHouseNumber = input.next();

        System.out.print("unesi grad u kojem je tvornica: ");
        String factoryCity = input.next();

        System.out.print("unesi postanski broj: ");
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

