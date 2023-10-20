package hr.java.production.main;

import hr.java.production.model.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main2 {
    private static final Integer NUMBER_OF_CATEGORIES = 2;  //3 kategorije
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
        System.out.println("__________________________________________________");

        //radi ok
        itemInput(input, categoryArray, itemArray);
        System.out.println("__________________________________________________");

        //odabir proizvoda ne radi
        factoryInput(input, numberOfItemsPerFactory, chosenItemsArray, itemArray, factoriesArray);
        System.out.println("__________________________________________________");


        storeInput(input, numberOfItemsPerStore, chosenItemsArray, itemArray, chosenStoreItemsArray, storeArray);
        System.out.println("__________________________________________________");

        BigDecimal maxVolume = null;
        Integer maxItemVolumeIndex = null;
        Integer maxFactoryVolumeIndex = null;


        for (int i = 0; i < factoriesArray.length; i++) {
            Item[] newItem = factoriesArray[i].getItems();

            for (int j = 0; j < newItem.length; j++) {

                BigDecimal itemVolume = newItem[j].getWidth().multiply(newItem[j].getHeight()).multiply(newItem[j].getLength());
                //izracun volumena radi

                if (maxVolume == null || itemVolume.compareTo(maxVolume) > 0) {
                    maxVolume = itemVolume;
                    maxFactoryVolumeIndex = i;
                }
            }

        }

        System.out.println("najveci volumen -> " + maxVolume);
        System.out.println("proizvela je tvornica -> " + factoriesArray[maxFactoryVolumeIndex].getName());

        BigDecimal minPrice = null;

        for (int i = 0; i < storeArray.length; i++) {
            Item[] newStoreItems = storeArray[i].getItems();

            for (int j = 0; j < newStoreItems.length; j++) {

                if (minPrice == null || newStoreItems[j].getSellingPrice().compareTo(minPrice) < 0) {
                    minPrice = newStoreItems[j].getSellingPrice();

                }
            }

        }
        System.out.println("Najmanja cijena -> " + minPrice);
        System.out.println("test line");

    }
    private static void storeInput(Scanner input, Integer numberOfItemsPerStore, Item[] chosenItemsArray, Item[] itemArray, Item[] chosenStoreItemsArray, Store[] storeArray) {
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

    private static void factoryInput(Scanner input, Integer numberOfItemsPerFactory, Item[] chosenItemsArray, Item[] itemArray, Factory[] factoriesArray) {
        for (int i = 0; i < NUMBER_OF_FACTORIES; i++){
            System.out.print("Upisi ime "+ (i + 1) +". tvornice: ");
            String factoryName = input.next();

            //za adresu
            /*
            System.out.print("Upisi ulicu "+ (i + 1) +". tvornice: ");
            String streetName = input.next();

            System.out.print("Upisi kucni broj "+ (i + 1) +". tvornice: ");
            String houseNumber = input.next();

            System.out.print("Upisi grad u kojem se nalazi "+ (i + 1) +". tvornica: ");
            String city = input.next();

            System.out.print("Upisi postanski broj "+ (i + 1) +". tvornice: ");
            String postalCode = input.next();

            Address newAddress = new Address(streetName,houseNumber,city,postalCode);

             */
            Address newAddress = new Address("gajeva","88","zd","10000");

            for (int j = 0; j < numberOfItemsPerFactory; j++){
                System.out.print("upisi koji se proizvod proizvodi u "+ (i + 1) + ". tvornici: ");
                Integer chosenItemInFactory = input.nextInt();


                //tu je problem
                chosenItemsArray[j] = itemArray[chosenItemInFactory - 1];

            }

            Factory newFactory = new Factory(factoryName,newAddress, chosenItemsArray);
            factoriesArray[i] = newFactory;
        }
    }

    private static void itemInput(Scanner input, Category[] categoryArray, Item[] itemArray) {
        for (int i = 0;i<NUMBER_OF_ITEMS;i++){
            System.out.print("unesi ime " + (i + 1) + ". predmeta: ");
            String itemName = input.next();

            System.out.print("Odaberite kategoriju (1-" + NUMBER_OF_CATEGORIES + "): ");
            Integer categoryIndex = input.nextInt();
            Category selectedCategory = categoryArray[categoryIndex - 1];

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

            Item newItem = new Item(itemName, selectedCategory, itemWidth, itemHeight,itemLenght, itemProductionCost, itemSellingPrice);
            itemArray[i] = newItem;

        }
    }

    private static void categoryInput(Scanner input, Category[] categoryArray) {
        for (int i = 0;i < NUMBER_OF_CATEGORIES; i++){
            System.out.print("unesi ime "+ (i+1) +". kategorije: ");
            String categoryName = input.next();
            input.nextLine();

            System.out.print("unesi opis "+ (i+1) +". kategorije: ");
            String categoryDescription = input.next();
            input.nextLine();

            Category newCategory = new Category(categoryName,categoryDescription);
            categoryArray[i] = newCategory;
        }
    }
}
