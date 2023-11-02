package hr.java.production.main;

import hr.java.production.exception.UniqueCategoryNameException;
import hr.java.production.exception.UniqueItemInFactoryException;
import hr.java.production.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    private static final Integer NUMBER_OF_CATEGORIES = 2;
    private static final Integer NUMBER_OF_ITEMS = 2;
    private static final Integer NUMBER_OF_FACTORIES = 1;
    private static final Integer NUMBER_OF_STORES = 1;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //logger.info("program je pokrenut");

        Integer numberOfItemsPerFactory = -1;
        Integer numberOfItemsPerStore = -1;
        Boolean errorFlag = false;

        do {
            errorFlag = false;

            try {
                System.out.print("koliko itema proizvodi svaka tvornica: ");
                numberOfItemsPerFactory = input.nextInt();

                if (numberOfItemsPerFactory < 0)
                    System.out.println("===KRIVI UPIS===");

            }catch (InputMismatchException ex){
                input.nextLine();
                System.out.println("Upisan je podatak koji nije Integer");
                logger.error("Upisan je podatak koji nije Integer " + ex);
                errorFlag = true;
            }

        }while (errorFlag || numberOfItemsPerFactory < 0);


        do {
            errorFlag = false;

            try {
                System.out.print("koliko itema ima svaki ducan: ");
                numberOfItemsPerStore = input.nextInt();

                if (numberOfItemsPerStore < 0)
                    System.out.println("===KRIVI UPIS===");

            }catch (InputMismatchException ex){
                input.nextLine();
                System.out.println("Upisan je podatak koji nije Integer");
                logger.error("Upisan je podatak koji nije Integer " + ex);
                errorFlag = true;
            }

        }while (errorFlag || numberOfItemsPerStore < 0);






        Category[] categoryArray = new Category[NUMBER_OF_CATEGORIES];
        Item[] itemArray = new Item[NUMBER_OF_ITEMS];
        Factory[] factoriesArray = new Factory[NUMBER_OF_FACTORIES];
        Store[] storeArray = new Store[NUMBER_OF_STORES];

        Item[] chosenStoreItemsArray = new Item[numberOfItemsPerStore];
        Item[] chosenItemsArray = new Item[numberOfItemsPerFactory];


        categoryInput(input, categoryArray);
        System.out.println("__________________________________________________");

        itemInput(input, categoryArray, itemArray);
        System.out.println("__________________________________________________");

        factoryInput(input, numberOfItemsPerFactory, chosenItemsArray, itemArray, factoriesArray);
        System.out.println("__________________________________________________");

        storeInput(input, numberOfItemsPerStore, chosenItemsArray, itemArray, chosenStoreItemsArray, storeArray);
        System.out.println("__________________________________________________");

        /*
        BigDecimal maxVolume = BigDecimal.ZERO;
        Integer maxItemVolumeIndex = 0;
        Integer maxFactoryVolumeIndex = 0;

        for (int i = 0; i < factoriesArray.length; i++) {
            Item[] newItem = factoriesArray[i].getItems();

            for (int j = 0; j < newItem.length; j++) {

                BigDecimal itemVolume = newItem[j].getWidth().multiply(newItem[j].getHeight()).multiply(newItem[j].getLenght());

                if (maxVolume == null || itemVolume.compareTo(maxVolume) > 0) {
                    maxVolume = itemVolume;
                    maxFactoryVolumeIndex = i;
                }
            }
        }


        System.out.println("najveci volumen -> " + maxVolume);
        System.out.println("proizvela je tvornica -> " + factoriesArray[maxFactoryVolumeIndex].getName());

        BigDecimal minPrice = BigDecimal.ZERO;
        Integer minPriceStoreIndex = 0;

        for (int i = 0; i < storeArray.length; i++) {
            Item[] newStoreItems = storeArray[i].getItems();

            for (int j = 0; j < newStoreItems.length; j++) {

                if (minPrice == null || newStoreItems[j].getSellingPrice().compareTo(minPrice) < 0) {
                    minPrice = newStoreItems[j].getSellingPrice();
                    minPriceStoreIndex = j;

                }
            }

        }
        System.out.println("Najmanja cijena -> " + minPrice);
        System.out.println("Ducan sa najmanjom cijenom -> " + storeArray[minPriceStoreIndex].getName());

        */
        BigDecimal maxPriceWeight = BigDecimal.ZERO;
        Integer maxPriceWeightIndex = 0;

        BigDecimal maxCalories = BigDecimal.ZERO;
        Integer maxCaloriesIndex = 0;

        for (int i=0;i<itemArray.length;i++){
            Item newItem = itemArray[i];

            if(newItem instanceof Edible food){
                if (food.calculatePrice().compareTo(maxPriceWeight) > 0){
                    maxPriceWeight = food.calculatePrice();
                    maxPriceWeightIndex = i;

                }
                if (food.calculateCalories().compareTo(maxCalories) > 0){
                    maxCalories = food.calculateCalories();
                    maxCaloriesIndex = i;
                }
            }
        }

        System.out.println("Najveca cijena s obzirom na masu -> " + itemArray[maxPriceWeightIndex].getName());
        System.out.println("Cijena -> " + maxPriceWeight);

        System.out.println("Najveci broj kalorija -> " + itemArray[maxCaloriesIndex].getName());
        System.out.println("Broj kalorija -> " + maxCalories);



        Integer minWarranty = null;
        Integer minWarrantyIndex = 0;

        for (int i=0;i<itemArray.length;i++){
            Item newItem = itemArray[i];

            if(newItem instanceof Laptop laptop){

                if (minWarranty == null || laptop.getWarrantyDuration().compareTo(minWarranty) < 0){
                    minWarranty = laptop.getWarrantyDuration();
                    minWarrantyIndex = i;
                }
            }
        }
        System.out.println("laptop sa najkracom garancijom -> " + itemArray[minWarrantyIndex].getName());
        System.out.println("duljina garancije -> " + minWarranty);

    }
    private static void storeInput(Scanner input, Integer numberOfItemsPerStore, Item[] chosenItemsArray, Item[] itemArray, Item[] chosenStoreItemsArray, Store[] storeArray) {
        for (int i = 0; i < NUMBER_OF_STORES; i++) {

            System.out.print("unesi ime ducana: ");
            String storeName = input.next();

            /*
            System.out.print("unesi web adresu ducana: ");
            String storeWebAddress = input.next();
             */

            Item[] newChosenStoreItem =  new Item[numberOfItemsPerStore];

            for (int j = 0; j < numberOfItemsPerStore; j++) {
                System.out.print("odaberi " + (j + 1) + " item koji se prodaje u ducanu: ");
                int chosenItemIndex = input.nextInt();
                newChosenStoreItem[j] = itemArray[chosenItemIndex - 1];
            }

            Store newStore = new Store(storeName, "hardcodaniWeb", newChosenStoreItem);
            storeArray[i] = newStore;

        }
    }

    private static void factoryInput(Scanner input, Integer numberOfItemsPerFactory, Item[] chosenItemsArray, Item[] itemArray, Factory[] factoriesArray) {
        for (int i = 0; i < NUMBER_OF_FACTORIES; i++) {
            System.out.print("Upisi ime " + (i + 1) + ". tvornice: ");
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

            Address newAddress = new Address("gajeva", "88", "zd", "10000");
            Item[] newChosenItemArray = new Item[numberOfItemsPerFactory];
            Integer chosenItemInFactoryIndex = -1;

            for (int j = 0; j < numberOfItemsPerFactory; j++) {

                Boolean uniqueItemFlag = false;
                do {
                    uniqueItemFlag = false;
                    System.out.print((j+1) + " proizvod koji se proizvodi u " + (i + 1) + ". tvornici: ");
                    chosenItemInFactoryIndex = input.nextInt();

                    Item newItem = itemArray[chosenItemInFactoryIndex - 1];

                    //radi ok
                    try {
                        checkUniqueItemInFactory(newItem, newChosenItemArray);

                    } catch (UniqueItemInFactoryException ex) {
                        System.out.println("u tvornicu je unesen isti item ");
                        logger.error("unesen je isti item u tvornicu" + ex);
                        uniqueItemFlag = true;
                    }
                    newChosenItemArray[j] = itemArray[chosenItemInFactoryIndex - 1];

                }while (uniqueItemFlag);
            }

            Factory newFactory = new Factory(factoryName,newAddress, newChosenItemArray);
            factoriesArray[i] = newFactory;


        }

    }

    private static void checkUniqueItemInFactory(Item newItem, Item[] chosenItemsArray) throws UniqueItemInFactoryException {
        for(Item item : chosenItemsArray){
            if (item != null){
                if (newItem.equals(item)){
                    throw new UniqueItemInFactoryException("korisnik je u tvornicu stavio item koji vec postoji");
                }
            }
        }
    }

    private static void itemInput(Scanner input, Category[] categoryArray, Item[] itemArray) {
        for (int i = 0;i<NUMBER_OF_ITEMS;i++){

            String isItemPredefined;
            do {

                System.out.println("da li je " + (i + 1) + ". proizvod preddefiniran: ");
                System.out.println("=== y za da === n za ne");
                System.out.println("preddefinirani proizvodi: jabuka, zito, laptop");
                isItemPredefined = input.next();

                if (!isItemPredefined.equals("n") && !isItemPredefined.equals("y")){

                    System.out.println();
                    System.out.println("===KRIVI UNOS===");
                    System.out.println();
                }

            }while (!isItemPredefined.equals("n") && !isItemPredefined.equals("y"));


            System.out.print("unesi ime " + (i + 1) + ". predmeta: ");
            String itemName = input.next();


            Integer categoryIndex = -1;
            Category selectedCategory = null;
            do {

                System.out.print("Odaberite kategoriju (1-" + NUMBER_OF_CATEGORIES + "): ");
                categoryIndex = input.nextInt();


                if (categoryIndex < 0 || categoryIndex > NUMBER_OF_CATEGORIES) {
                    System.out.println("===UNESENA JE KATEGORIJA KOJA NE POSTOJI===");
                }else {
                    selectedCategory = categoryArray[categoryIndex - 1];
                }

            }while (categoryIndex < 0 || categoryIndex > NUMBER_OF_CATEGORIES);


            //velicine jabuka i zita su hardkodirane
            BigDecimal itemWidth = null;
            BigDecimal itemHeight = null;
            BigDecimal itemLenght = null;


            if (isItemPredefined.equals("n")) {
                System.out.print("unesi sirinu predmeta: ");
                itemWidth = input.nextBigDecimal();
                input.nextLine();

                System.out.print("unesi visinu predmeta: ");
                itemHeight = input.nextBigDecimal();
                input.nextLine();

                System.out.print("unesi duzinu predmeta: ");
                itemLenght = input.nextBigDecimal();
                input.nextLine();
            }

            System.out.print("unesi trosak proizvodnje: ");
            BigDecimal itemProductionCost = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi cijenu: ");
            BigDecimal itemSellingPrice = input.nextBigDecimal();
            input.nextLine();

            System.out.print("unesi popust u postotku: ");
            BigDecimal itemDiscount = input.nextBigDecimal();
            input.nextLine();


            if (Objects.equals(isItemPredefined, "y")) {
                System.out.print("odaberi proizvod:\n1. jabuka\n2. zito\n3. laptop\n");
                Integer predefinedItem = input.nextInt();
                input.nextLine();

                if (predefinedItem.equals(1)){
                    System.out.print("unesi kolicinu namirnice u kilogramima: ");
                    BigDecimal weight = input.nextBigDecimal();
                    input.nextLine();

                    Apple newApple = new Apple(itemName, selectedCategory, BigDecimal.valueOf(2), BigDecimal.valueOf(2), BigDecimal.valueOf(2), itemProductionCost, itemSellingPrice,itemDiscount, weight);
                    itemArray[i] = newApple;


                }else if (predefinedItem.equals(2)){
                    System.out.print("unesi kolicinu namirnice u kilogramima: ");
                    BigDecimal weight = input.nextBigDecimal();
                    input.nextLine();

                    Wheat newWheat = new Wheat(itemName, selectedCategory, BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1), itemProductionCost, itemSellingPrice,itemDiscount, weight);
                    itemArray[i] = newWheat;


                }else if(predefinedItem.equals(3)){
                    System.out.print("koliko dugo traje garancija: ");
                    Integer warranty = input.nextInt();
                    input.nextLine();

                    Laptop newLaptop = new Laptop(itemName, selectedCategory, itemWidth, itemHeight, itemLenght, itemProductionCost, itemSellingPrice, itemDiscount, warranty);
                    itemArray[i] = newLaptop;

                    System.out.println(newLaptop.getWarrantyDuration());
                }


            }else {
                Item newItem = new Item(itemName, selectedCategory, itemWidth, itemHeight, itemLenght, itemProductionCost, itemSellingPrice,itemDiscount);
                itemArray[i] = newItem;
            }
        }
    }

    private static void categoryInput(Scanner input, Category[] categoryArray) {

        for (int i = 0;i < NUMBER_OF_CATEGORIES; i++) {

            String categoryName;
            String categoryDescription;

            Category newCategory;
            Boolean uniqueItemFlag = false;

            //radi ok
            do {
                uniqueItemFlag = false;

                System.out.print("unesi ime " + (i + 1) + ". kategorije: ");
                categoryName = input.next();
                input.nextLine();

                try {
                    checkUniqueCategoryName(categoryName, categoryArray);

                } catch (UniqueCategoryNameException ex) {
                    System.out.println("uneseno je isto ime kategorije ");
                    logger.error("uneseno je isto ime kategorije" + ex);
                    uniqueItemFlag = true;
                }
            }while (uniqueItemFlag);
            /*
            System.out.print("unesi opis " + (i + 1) + ". kategorije: ");
            categoryDescription = input.nextLine();
            */

            newCategory = new Category(categoryName, "hardkodirano");
            categoryArray[i] = newCategory;


        }
    }

    private static void checkUniqueCategoryName(String categoryName, Category[] categoryArray) throws UniqueCategoryNameException {
        for (Category newCategory : categoryArray){
            if (newCategory != null){
                if (categoryName.equals(newCategory.getName())){
                    throw new UniqueCategoryNameException("unesena kategorija vec postoji");
                }
            }
        }
    }
}
