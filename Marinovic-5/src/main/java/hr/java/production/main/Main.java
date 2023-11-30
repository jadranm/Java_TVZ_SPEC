package hr.java.production.main;

import hr.java.production.enumeration.CitiesEnum;
import hr.java.production.exception.UniqueCategoryNameException;
import hr.java.production.exception.UniqueItemInFactoryException;
import hr.java.production.genericsi.FoodStore;
import hr.java.production.genericsi.TehnicalStore;
import hr.java.production.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


public class Main {
    private static final Integer NUMBER_OF_CATEGORIES = 1;
    private static final Integer NUMBER_OF_ITEMS = 3;
    private static final Integer NUMBER_OF_FACTORIES = 1;
    private static final Integer NUMBER_OF_STORES = 1;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static <T> void main(String[] args) {
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
        Factory[] factoriesArray = new Factory[NUMBER_OF_FACTORIES];

        List<Store> storeList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();
        List<Item> chosenStoreItemsList = new ArrayList<>();
        List<Item> chosenItemsList = new ArrayList<>();

        Map<Category,List<Item>> categoryItemMap = new HashMap<>();


        List<Store> listOfTehnicalStores = new ArrayList<>();
        List<Store> listOfFoodStores = new ArrayList<>();

        categoryInput(input, categoryArray);
        System.out.println("__________________________________________________");

        itemInput(input, categoryArray, itemList,categoryItemMap);
        System.out.println("__________________________________________________");

        factoryInput(input, numberOfItemsPerFactory, chosenItemsList, itemList, factoriesArray);
        System.out.println("__________________________________________________");

        storeInput(input, numberOfItemsPerStore, chosenItemsList, itemList, chosenStoreItemsList, storeList,listOfTehnicalStores);
        System.out.println("__________________________________________________");

        paramStoreInput(input, itemList,listOfTehnicalStores, listOfFoodStores, storeList);
        System.out.println("__________________________________________________");
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


        BigDecimal maxPriceWeight = BigDecimal.ZERO;
        Integer maxPriceWeightIndex = 0;

        BigDecimal maxCalories = BigDecimal.ZERO;
        Integer maxCaloriesIndex = 0;


        for (int i=0;i<itemList.size();i++){
            Item newItem = itemList.get(i);

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

        System.out.println("Najveca cijena s obzirom na masu -> " + itemList.get(maxPriceWeightIndex).getName());
        System.out.println("Cijena -> " + maxPriceWeight);

        System.out.println("Najveci broj kalorija -> " + itemList.get(maxCaloriesIndex).getName());
        System.out.println("Broj kalorija -> " + maxCalories);



        Integer minWarranty = null;
        Integer minWarrantyIndex = 0;

        for (int i=0;i<itemList.size();i++){
            Item newItem = itemList.get(i);

            if(newItem instanceof Laptop laptop){

                if (minWarranty == null || laptop.getWarrantyDuration().compareTo(minWarranty) < 0){
                    minWarranty = laptop.getWarrantyDuration();
                    minWarrantyIndex = i;
                }
            }
        }
        System.out.println("laptop sa najkracom garancijom -> " + itemList.get(minWarrantyIndex).getName());
        System.out.println("duljina garancije -> " + minWarranty);




        //Collections.sort(itemList, new ProductionSorter(true));
        //itemList.stream().forEach(System.out :: println);

        for (int i = 0;i < NUMBER_OF_CATEGORIES;i++){
            Category mapKey = categoryArray[i];

            if (!categoryItemMap.get(mapKey).isEmpty()) {
                System.out.println("min i max za kategoriju " + mapKey.getName());
                System.out.println();
                Collections.sort(categoryItemMap.get(mapKey),new ProductionSorter(true));
                System.out.println("minumum -> " + categoryItemMap.get(mapKey).getFirst().getName() + " "
                        + categoryItemMap.get(mapKey).getFirst().getSellingPrice());

                System.out.println("maksimum -> " + categoryItemMap.get(mapKey).getLast().getName() + " "
                        + categoryItemMap.get(mapKey).getLast().getSellingPrice());
            }

        }







        //good shit

        List<Item> listOfEdibleItems = new ArrayList<>();
        List<Item> listOfTehnicalItems = new ArrayList<>();

        for (Item newItem : itemList){

            if (newItem instanceof Edible){
                listOfEdibleItems.add(newItem);
            }else if (newItem instanceof Tehnical){
                listOfTehnicalItems.add(newItem);
            }
        }



        if (!listOfEdibleItems.isEmpty()) {
            System.out.println("min i max jestivih predmeta");
            Collections.sort(listOfEdibleItems, new ProductionSorter(true));

            System.out.println("min -> " + listOfEdibleItems.getFirst().getName() + " "
                    + listOfEdibleItems.getFirst().getSellingPrice());

            System.out.println("max -> " + listOfEdibleItems.getLast().getName() + " "
                    + listOfEdibleItems.getLast().getSellingPrice());
        }

        if (!listOfTehnicalItems.isEmpty()) {
            System.out.println("min i max tehnickih predmeta");
            Collections.sort(listOfTehnicalItems, new ProductionSorter(true));

            System.out.println("min -> " + listOfTehnicalItems.getFirst().getName() + " "
                    + listOfTehnicalItems.getFirst().getSellingPrice());

            System.out.println("max -> " + listOfTehnicalItems.getLast().getName() + " "
                    + listOfTehnicalItems.getLast().getSellingPrice());
        }


        //System.out.println("prije");

        for (Item testItem : itemList){
            System.out.println(testItem.getName() + " " + testItem.getVolume());
        }
        for (Store testStore : storeList){
            testStore.getItems().sort((i1, i2) -> i1.getVolume().compareTo(i2.getVolume()));
        }

         */

        //lv5 priprema
        itemList.sort((i1, i2) -> i1.getVolume().compareTo(i2.getVolume()));
        System.out.println("sortirani itemi: ");
        itemList.forEach(item -> System.out.println(item.getName() + " - " + item.getVolume()));
        long startTime = System.currentTimeMillis();

        BigDecimal averageVolume = itemList.stream().map(Item::getVolume).reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(itemList.size()), RoundingMode.CEILING);

        Optional<List<Item>> filteredItems = Optional.of(itemList.stream().filter(item -> item.getVolume().compareTo(averageVolume) > 0).toList());

        System.out.println("average volume " + averageVolume);
        System.out.println("itemi sa nadprosjecnim volumenom: ");
        filteredItems.ifPresent(System.out :: println);

        List<Item> discountGraterThen0List = itemList.stream().filter(item -> item.getDiscountAmount().compareTo(BigDecimal.ZERO) > 0).toList();
        discountGraterThen0List.forEach(item -> System.out.println("item sa popustom vecim od 0 " + item.getName() + " popust " + item.getDiscountAmount() + " %"));

        storeList.forEach(store -> System.out.println("broj itema u " + store.getName() + " je " + store.getItems().size()));

        System.out.println("__________________________________________________");
        System.out.println("Vrijeme izvođenja svih lamda funkcija: " + (System.currentTimeMillis() - startTime) + " ms");



        //----------------
        //Kraj main metode
        //----------------

    }

    private static void paramStoreInput(Scanner input, List<Item> itemList, List<Store> listOfTehnicalStores , List<Store> listOfFoodStores, List<Store> storeList) {

        List<Item> listOfEdibleItems = new ArrayList<>();
        List<Item> listOfTehnicalItems = new ArrayList<>();

        for (Item newItem : itemList){
            if (newItem instanceof Edible){
                listOfEdibleItems.add(newItem);
            }else if (newItem instanceof Tehnical){
                listOfTehnicalItems.add(newItem);
            }
        }

        System.out.print("unesi ime tehnical ducana: ");
        String tehnicalStoreName = input.next();

        TehnicalStore<Tehnical> newTehnicalStore = new TehnicalStore<>(tehnicalStoreName,"hardkodiraniWeb",listOfTehnicalItems);
        listOfTehnicalStores.add(newTehnicalStore);
        storeList.add(newTehnicalStore);



        System.out.print("unesi ime edible ducana: ");
        String edibleStoreName = input.next();

        FoodStore<Edible> newFoodStore = new FoodStore<>(edibleStoreName,"hardkodiraniWeb",listOfTehnicalItems);
        listOfFoodStores.add(newFoodStore);
        storeList.add(newFoodStore);

        System.out.println("testLine");

    }

    /**
     * Metoda za unos podataka o dućanima i proizvodima koje prodaju.
     *
     * @param input                 Scanner objekt za unos podataka.
     * @param numberOfItemsPerStore Broj stavki koje korisnik može odabrati za svaki dućan.
     * @param chosenItemsArray      Polje odabranih stavki.
     * @param itemList              Polje svih dostupnih stavki.
     * @param chosenStoreItemsArray Polje stavki koje su odabrane za pojedini dućan.
     * @param storeList            Lista objekata klase Store za pohranu unesenih podataka o dućanima.
     * @param listOfTehnicalStores
     */
    private static <T> void storeInput(Scanner input, Integer numberOfItemsPerStore, List<Item> chosenItemsArray,
                                       List<Item> itemList, List<Item> chosenStoreItemsArray, List<Store> storeList, List<Store> listOfTehnicalStores) {


        for (int i = 0; i < NUMBER_OF_STORES; i++) {

            System.out.print("unesi ime ducana: ");
            String storeName = input.next();

            /*
            System.out.print("unesi web adresu ducana: ");
            String storeWebAddress = input.next();
             */

            List<Item> newChosenStoreItemList = new ArrayList<>();

            for (int j = 0; j < numberOfItemsPerStore; j++) {
                System.out.print("odaberi " + (j + 1) + " item koji se prodaje u ducanu: ");
                int chosenItemIndex = input.nextInt();
                //newChosenStoreItem[j] = itemArray[chosenItemIndex - 1];

                newChosenStoreItemList.add(j, itemList.get((chosenItemIndex - 1)));
            }

            Store newStore = new Store(storeName, "hardcodaniWeb", newChosenStoreItemList);
            storeList.add(newStore);
        }

    }

    /**
     * Metoda za unos podataka o tvornicama i proizvodima koje proizvode.
     *
     * @param input Scanner objekt za unos podataka.
     * @param numberOfItemsPerFactory Broj stavki koje korisnik može odabrati za svaku tvornicu.
     * @param chosenItemsList Lista odabranih stavki.
     * @param itemList Lista svih dostupnih stavki.
     * @param factoriesArray Polje objekata klase Factory za pohranu unesenih podataka o tvornicama.
     */
    private static void factoryInput(Scanner input, Integer numberOfItemsPerFactory, List<Item> chosenItemsList, List<Item> itemList, Factory[] factoriesArray) {
        for (int i = 0; i < NUMBER_OF_FACTORIES; i++) {
            System.out.print("Upisi ime " + (i + 1) + ". tvornice: ");
            String factoryName = input.next();


            //za adresu

            String isCityPredefined;
            String city = null, postalCode = null;

            do {
                System.out.println("da li je grad preddefiniran y za da n za ne");
                System.out.println("Preddefinirani gradovi Zagreb, Split, Osijek, Rijeka ");
                isCityPredefined = input.next();

                if (isCityPredefined.equals("y")) {

                    System.out.println("upisi redni brog grada: ");
                    Integer chosenCity = input.nextInt();

                    if (chosenCity.equals(1)) {
                        CitiesEnum newCity = CitiesEnum.ZAGREB;
                        city = newCity.getCityName();
                        postalCode = newCity.getZipCode();

                    } else if (chosenCity.equals(2)) {
                        CitiesEnum newCity = CitiesEnum.SPLIT;
                        city = newCity.getCityName();
                        postalCode = newCity.getZipCode();

                    } else if (chosenCity.equals(3)) {
                        CitiesEnum newCity = CitiesEnum.OSIJEK;
                        city = newCity.getCityName();
                        postalCode = newCity.getZipCode();

                    } else if (chosenCity.equals(4)){
                        CitiesEnum newCity = CitiesEnum.RIJEKA;
                        city = newCity.getCityName();
                        postalCode = newCity.getZipCode();
                    }

                } else if (isCityPredefined.equals("n")) {
                    System.out.print("Upisi grad u kojem se nalazi " + (i + 1) + ". tvornica: ");
                    city = input.next();

                    System.out.print("Upisi postanski broj " + (i + 1) + ". tvornice: ");
                    postalCode = input.next();
                }

                if (!isCityPredefined.equals("n") && !isCityPredefined.equals("y")){
                    System.out.println();
                    System.out.println("===KRIVI UNOS===");
                    System.out.println();
                }

            }while (!isCityPredefined.equals("n") && !isCityPredefined.equals("y"));

            /*
            System.out.print("Upisi ulicu "+ (i + 1) +". tvornice: ");
            String streetName = input.next();

            System.out.print("Upisi kucni broj "+ (i + 1) +". tvornice: ");
            String houseNumber = input.next();

            */

            Address newAddress = new Address("harkodirano","11",city,postalCode);


            //Address newAddress = new Address("gajeva", "88", "zd", "10000");1
            List<Item> newChosenItemList = new ArrayList<>();

            Integer chosenItemInFactoryIndex = -1;

            for (int j = 0; j < numberOfItemsPerFactory; j++) {

                Boolean uniqueItemFlag = false;
                do {
                    uniqueItemFlag = false;
                    System.out.print((j+1) + " proizvod koji se proizvodi u " + (i + 1) + ". tvornici: ");
                    chosenItemInFactoryIndex = input.nextInt();

                    Item newItem = itemList.get(chosenItemInFactoryIndex - 1);

                    //radi ok
                    try {
                        checkUniqueItemInFactory(newItem, newChosenItemList);

                    } catch (UniqueItemInFactoryException ex) {
                        System.out.println("u tvornicu je unesen isti item ");
                        logger.error("unesen je isti item u tvornicu " + ex);
                        uniqueItemFlag = true;
                    }


                }while (uniqueItemFlag);
            }

            Factory newFactory = new Factory(factoryName,newAddress, newChosenItemList);
            factoriesArray[i] = newFactory;




        }

    }

    /**
     * Provjerava je li novi proizvod jedinstven u odabranoj tvornici.
     *
     * @param newItem Proizvod koji se provjerava na jedinstvenost.
     * @param chosenItemsArray Polje postojećih proizvoda u tvornici.
     * @throws UniqueItemInFactoryException Ako se novi proizvod već nalazi u tvornici, baca se iznimka.
     */
    private static void checkUniqueItemInFactory(Item newItem, List<Item> chosenItemsArray) throws UniqueItemInFactoryException {
        for(Item item : chosenItemsArray){
            if (item != null){
                if (newItem.equals(item)){
                    throw new UniqueItemInFactoryException("korisnik je u tvornicu stavio item koji vec postoji ");
                }
            }
        }
    }


    /**
     * Metoda za unos podataka o predmetima (proizvodima).
     *
     * @param input Scanner objekt za unos podataka.
     * @param categoryArray Polje dostupnih kategorija proizvoda.
     * @param itemList Lista objekata klase Item za pohranu unesenih podataka o proizvodima.
     */
    private static void itemInput(Scanner input, Category[] categoryArray, List<Item> itemList, Map categoryItemMap) {
        for (int i = 0;i<NUMBER_OF_ITEMS;i++) {

            String isItemPredefined;
            do {

                System.out.println("da li je " + (i + 1) + ". proizvod preddefiniran: ");
                System.out.println("=== y za da === n za ne");
                System.out.println("preddefinirani proizvodi: jabuka, zito, laptop");
                isItemPredefined = input.next();

                if (!isItemPredefined.equals("n") && !isItemPredefined.equals("y")) {

                    System.out.println();
                    System.out.println("===KRIVI UNOS===");
                    System.out.println();
                }

            } while (!isItemPredefined.equals("n") && !isItemPredefined.equals("y"));


            System.out.print("unesi ime " + (i + 1) + ". predmeta: ");
            String itemName = input.next();

            Integer categoryIndex = -1;
            Category selectedCategory = null;

            Boolean errorFlag;

            do {
                errorFlag = false;
                try {
                    System.out.print("Odaberite kategoriju (1-" + NUMBER_OF_CATEGORIES + "): ");
                    categoryIndex = input.nextInt();


                    if (categoryIndex < 0 || categoryIndex > NUMBER_OF_CATEGORIES) {
                        System.out.println("===UNESENA JE KATEGORIJA KOJA NE POSTOJI===");

                    } else {
                        selectedCategory = categoryArray[categoryIndex - 1];

                    }

                } catch (InputMismatchException ex) {
                    input.nextLine();
                    System.out.println("Upisan je podatak koji nije Integer ");
                    logger.error("Pri upisu kategorije upisan je podatak koji nije Integer " + ex);
                    errorFlag = true;

                }
            } while (errorFlag || categoryIndex < 0 || categoryIndex > NUMBER_OF_CATEGORIES);


            //velicine jabuka i zita su hardkodirane
            BigDecimal itemLenght = null,
                    itemHeight = null,
                    itemWidth = null,
                    itemProductionCost = null,
                    itemSellingPrice = null,
                    itemDiscount = null;

            Boolean mesurmentsError;

            do {
                mesurmentsError = false;
                try {
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
                    itemProductionCost = input.nextBigDecimal();
                    input.nextLine();

                    System.out.print("unesi cijenu: ");
                    itemSellingPrice = input.nextBigDecimal();
                    input.nextLine();

                    System.out.print("unesi popust u postotku: ");
                    itemDiscount = input.nextBigDecimal();
                    input.nextLine();

                } catch (InputMismatchException ex) {
                    input.nextLine();
                    System.out.println("unesene dimenzije proizvoda nisu tipa Integer ");
                    logger.error("unesene dimenzije proizvoda nisu tipa Integer " + ex);
                    mesurmentsError = true;
                }
            } while (mesurmentsError);


            if (Objects.equals(isItemPredefined, "y")) {

                Integer predefinedItem = null;
                Boolean itemErrorFlag;

                do {
                    itemErrorFlag = false;
                    try {
                        System.out.print("odaberi proizvod:\n1. jabuka\n2. zito\n3. laptop\n");
                        predefinedItem = input.nextInt();
                        input.nextLine();

                    } catch (InputMismatchException ex) {
                        itemErrorFlag = true;
                        input.nextLine();
                        System.out.println("Pri odabiru preddefiniranog proizvoda upisan je podatak koji nije Integer ");
                        logger.error("Pri odabiru preddefiniranog proizvoda upisan je podatak koji nije Integer " + ex);


                    }
                } while (itemErrorFlag || predefinedItem < 0);

                Boolean itemWeightErrorFlag;
                do {
                    itemWeightErrorFlag = false;
                    try {


                        if (predefinedItem.equals(1)) {
                            System.out.print("unesi kolicinu namirnice u kilogramima: ");
                            BigDecimal weight = input.nextBigDecimal();
                            input.nextLine();

                            Apple newApple = new Apple(itemName, selectedCategory, BigDecimal.valueOf(2), BigDecimal.valueOf(2), BigDecimal.valueOf(2), itemProductionCost, itemSellingPrice, itemDiscount, weight);
                            itemList.add(newApple);


                        } else if (predefinedItem.equals(2)) {
                            System.out.print("unesi kolicinu namirnice u kilogramima: ");
                            BigDecimal weight = input.nextBigDecimal();
                            input.nextLine();

                            Wheat newWheat = new Wheat(itemName, selectedCategory, BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1), itemProductionCost, itemSellingPrice, itemDiscount, weight);
                            itemList.add(newWheat);


                        } else if (predefinedItem.equals(3)) {
                            System.out.print("koliko dugo traje garancija: ");
                            Integer warranty = input.nextInt();
                            input.nextLine();

                            Laptop newLaptop = new Laptop(itemName, selectedCategory, itemWidth, itemHeight, itemLenght, itemProductionCost, itemSellingPrice, itemDiscount, warranty);
                            itemList.add(newLaptop);

                            System.out.println(newLaptop.getWarrantyDuration());
                        }
                    }catch (InputMismatchException ex){
                        itemWeightErrorFlag = true;
                        input.nextLine();
                        System.out.println("Pri odabiru kolicine proizvoda ili duljine garancije upisan je podatak koji nije Integer ");
                        logger.error("Pri odabiru kolicine proizvoda ili duljine garancije upisan je podatak koji nije Integer " + ex);
                    }

                }while (itemWeightErrorFlag);



            } else {
                Item newItem = new Item(itemName, selectedCategory, itemWidth, itemHeight, itemLenght, itemProductionCost, itemSellingPrice, itemDiscount);
                itemList.add(newItem);
            }

        }
            for (int i = 0;i<NUMBER_OF_CATEGORIES;i++) {
                List<Item> listWithSameCategory = new ArrayList<>();

                String searchedCategoryName = categoryArray[i].getName();

                for (Item newItem : itemList) {
                    Category newItemCategory = newItem.getCategory();
                    String newItemCategoryName = newItemCategory.getName();

                    if (searchedCategoryName.equals(newItemCategoryName)){
                        listWithSameCategory.add(newItem);
                    }
                }
                categoryItemMap.put(categoryArray[i],listWithSameCategory);
            }

        //System.out.println("test");




    }

    /**
     * Metoda za unos podataka o kategorijama proizvoda.
     *
     * @param input Scanner objekt za unos podataka.
     * @param categoryArray Polje objekata klase Category za pohranu unesenih podataka o kategorijama.
     */
    private static void categoryInput(Scanner input, Category[] categoryArray) {

        for (int i = 0;i < NUMBER_OF_CATEGORIES; i++) {

            String categoryName;
            String categoryDescription;

            Category newCategory;
            Boolean uniqueItemFlag;

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
                    logger.error("uneseno je isto ime kategorije " + ex);
                    uniqueItemFlag = true;
                }
            }while (uniqueItemFlag);

            newCategory = new Category(categoryName, "hardkodirano");
            categoryArray[i] = newCategory;
        }
    }

    /**
     * Provjerava je li uneseno ime kategorije jedinstveno u odnosu na postojeće kategorije.
     *
     * @param categoryName Ime kategorije koje se provjerava na jedinstvenost.
     * @param categoryArray Polje postojećih kategorija.
     * @throws UniqueCategoryNameException Ako se uneseno ime kategorije već nalazi među postojećim kategorijama, baca se iznimka.
     */
    private static void checkUniqueCategoryName(String categoryName, Category[] categoryArray) throws UniqueCategoryNameException {
        for (Category newCategory : categoryArray){
            if (newCategory != null){
                if (categoryName.equals(newCategory.getName())){
                    throw new UniqueCategoryNameException("unesena kategorija vec postoji ");
                }
            }
        }
    }
}
